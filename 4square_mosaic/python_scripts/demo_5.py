import Image

image64 = Image.open("fsquare_blue_transparent.png")
image128 = Image.open("final.png")

image64 = image64.convert("RGBA")
image128 = image128 = image128.convert("RGBA")

new_image = Image.blend(image64, image128, 0.5)

new_image.save("awesome.png", "PNG")