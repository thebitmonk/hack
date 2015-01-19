import Image, ImageFont, ImageDraw
    


words = [
    ((10, 10), "Red", "#ff0000", 30),
    ((10, 50), "Green", "#00ff00", 30),
    ((10, 90), "Blue", "#0000ff", 30),
    ((10, 130), "White", "#ffffff", 30),
    ((10, 170), "Black", "#000000", 30),
    ]

# A fully transparent image to work on.
im = Image.new("RGBA", (120, 210), (0,0,0,0))
dr = ImageDraw.Draw(im)

for pos, text, color, size in words:
    dr.text(pos, text,  fill=color)

im.save("badtranstext.png", "PNG")