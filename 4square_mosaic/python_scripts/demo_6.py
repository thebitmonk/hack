from PIL import Image

overlay = Image.open('fsquare_blue_transparent.png')
base = Image.open('final.png')

bands = list(overlay.split())
if len(bands) == 4:
    # Assuming alpha is the last band
    bands[3] = bands[3].point(lambda x: x)
overlay = Image.merge(overlay.mode, bands)

base.paste(overlay, (0, 0), overlay)
base.save('result.png')
