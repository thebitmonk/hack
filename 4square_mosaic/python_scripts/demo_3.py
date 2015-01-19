import Image
import os

blank_image = Image.open("fsquare.png")
files = os.listdir('.')
x = 0
y = 0
total_files = len(files)
num = 0
while(1):
	if num == total_files:
		num = 0
	fname = files[num]

	try:
		image = Image.open(fname)
		blank_image.paste(image, (x, y))
	except:
		print "error"
	x = x + 25
	if(x == 1000):
		x = 0
		y = y + 25
	if y == 600:
		break

	num = num + 1
	print "%s, %s"%(x, y)



out = "final.png"
blank_image.save(out)