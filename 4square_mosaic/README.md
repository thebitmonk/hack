foursquare_mosaic
=================

This app is a result of Foursquare hackathon. The initial idea was to make a command line utility that created a mosaic of your foursquare friends and embeds them inside
foursquare logo. The scripts are committed inside python_scripts folder.  Later we decided to make it a realtime webapp which does it on the fly. The above code as of now runs
on Google App Engine and uses Python Imaging Library for image manipulation and blobs for temporary storage. Via Oauth mechanism we retrieve users foursquare friends profile pics and create a composite mosaic. Using the same premise the optimized version of the code can be used for creating CDN for the company capable of returning images of different sizes on the fly. For a simple demo you can try http://99mosaics.appspot.com 
