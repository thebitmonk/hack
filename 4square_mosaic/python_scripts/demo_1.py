from PIL import Image

img = Image.open('fsquare.png')
img = img.convert("RGBA")
datas = img.getdata()
newData = list()

for item in datas:
  if item[0] <= 100 and item[0] >= 15 and item[2] >= 90 and item[3] >= 200:
    newData.append((255, 255, 255, 0))
  else:
    newData.append(item)

img.putdata(newData)
img.save("fsquare_blue_transparent.png")
