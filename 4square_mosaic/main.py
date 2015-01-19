# Main application file: "webapp/main.py"
import os
import logging

from google.appengine.api import urlfetch
from google.appengine.api import images
from google.appengine.api import memcache, users
from google.appengine.ext import db, webapp
from google.appengine.ext import blobstore
from google.appengine.ext.webapp.template import render
from google.appengine.ext.webapp.util import run_wsgi_app
from google.appengine.api import files
from django.utils import simplejson as json

friend_pics = []
client_id = 'MPHTFGSK5M44W0FSTKJHYQEDJRE2HBRDLB0OSGWDISFDKNHK'
client_secret = '3DYU1QA20QDZQUML3VXJVCB1MYH32W4ZTKEZBBPHNOTXMMAK'
redirect_uri = 'http://localhost:8080/home'
mosaic_height = 1000
mosaic_width = 600
#They must be a divisor of mosaic width and height
small_pic_xy = 100


class Photo(db.Model):
    title = db.StringProperty()
    picture = db.BlobProperty()

class MainHandler(webapp.RequestHandler):
    def get(self):
        user = 'Bitmonk'
        authentication_url = ''.join(["https://foursquare.com/oauth2/authenticate?client_id=",client_id, "&response_type=code&redirect_uri=", redirect_uri])
        context = {
            'user':      user,
            'authentication_url': authentication_url,
        }
        tmpl = os.path.join(os.path.dirname(__file__), 'index.html')
        self.response.out.write(render(tmpl, context))

class GetImage(webapp.RequestHandler):
    def get(self):

        title = self.request.get('title')
        friend_pic = getFriendPic(title)
        
        if (friend_pic and friend_pic.picture):
            self.response.headers['Content-Type'] = 'image/jpeg'
            self.response.out.write(friend_pic.picture)
        else:
            self.redirect('/static/noimage.jpg')

class HomeHandler(webapp.RequestHandler):
    def get(self):

        query = blobstore.BlobInfo.all()
        blobs = query.fetch(500)
        if len(blobs) > 0:
            for blob in blobs:
                blob.delete()

        code = self.request.get('code')

        url = ''.join(['https://foursquare.com/oauth2/access_token?client_id=',client_id
        ,'&client_secret='
        ,client_secret
        ,'&grant_type=authorization_code'
        ,'&redirect_uri='
        ,redirect_uri
        ,'&code='
        ,code]);

        data =  urlfetch.fetch(url)
        json_data = json.loads(data.content)
        oauth_token = json_data['access_token']
        getFriendsThumbnails(oauth_token)
        x = 0
        y = 0
            
        total_friends = len(friend_pics)
        index = 0
        while(1):
            index = index % total_friends
            title = friend_pics[index]
            index = index + 1
            mosaic = getFriendPic(title)
            if x == 0 and y == 0:
                x = x + small_pic_xy
                composite = images.composite([(mosaic.picture, 0, 0, 1.0, images.TOP_LEFT)], mosaic_width, mosaic_height)                    
            else:
                composite = images.composite([(composite, 0, 0, 1.0, images.TOP_LEFT),
                    (mosaic.picture, x, y, 1.0, images.TOP_LEFT)], mosaic_width, mosaic_height)
                x = x + small_pic_xy
                if(x >= mosaic_width):
                    x = 0
                    y = y + small_pic_xy
                if(y >= mosaic_height):
                    break

        self.response.headers['Content-Type'] = 'image/jpeg'
        self.response.out.write(composite)
        '''
        context = {}
        tmpl = os.path.join(os.path.dirname(__file__), 'home.html')
        self.response.out.write(render(tmpl, context))
        '''

application = webapp.WSGIApplication([
    ( '/', MainHandler),
    ('/home', HomeHandler),
    # The image url is of the format http://localhost:8080/image?title=CPX1T1G50441AILS
    ('/image', GetImage),

], debug=True)




def getFriendPic(title):
    result = db.GqlQuery("SELECT * FROM Photo WHERE title = :1 LIMIT 1",
                    title).fetch(1)
    if (len(result) > 0):
        return result[0]
    else:
        return None    

def getFriendsThumbnails(oauth_token):

    friend_api_endpoint = ''.join(['https://api.foursquare.com/v2/users/self/friends?oauth_token=', oauth_token])
    data = urlfetch.fetch(friend_api_endpoint)
    friend_data = json.loads(data.content)
    friends = friend_data['response']['friends']['items']

    for entry in friends:
        try:
            url = entry['photo']
            friend_pic = urlfetch.fetch(url)
            logging.warning(url)
            friend_pic_title = getImageName(url)
            friend_pics.append(friend_pic_title)
            logging.warning(friend_pic_title)
            if friend_pic_title:
                photo = Photo()
                photo.title = friend_pic_title
                photo.picture = db.Blob(friend_pic.content)
                photo.put()
                
        except:
            logging.warning('Some error happened')



        # printing friends url

def getImageName(url):
    try:
        url_parts = url.split('/')
        image_name = url_parts[len(url_parts) - 1]
        image_parts = image_name.split('.')
        image_title = image_parts[0]
        return image_title
    except:
        logging.error('Couldnt retrieve file name from url')
        return none

def main():
    run_wsgi_app(application)

if __name__ == '__main__':
    main()
