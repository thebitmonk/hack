#!/bin/sh
FILES=/var/www/server/static/images/*
IMAGES=`find $FILES -iname *.jpg -or -iname *.png`
for f in $IMAGES
do
   `convert -interlace Plane $f $f`
done
