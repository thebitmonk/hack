#!/bin/sh
## Specify data base schemas to backup and credentials
DATABASES="dhinchak"

## Syntax databasename as per above _USER and _PW
## _USER is mandatory _PW is optional
dhinchak_USER=root
dhinchak_PW=mysql

## Initialize some variables
DATE=$(date +%Y%m%d)
DATETIME=$(date +%Y%m%d-%H%m)
BACKUP_DIRECTORY=/tmp
S3_CMD="/usr/bin/s3cmd"

## Specify where the backups should be placed
S3_BUCKET_URL=s3://dhinchak

## The script
cd /
#mkdir -p $BACKUP_DIRECTORY

## Backup MySQL:s
for DB in $DATABASES
do
BACKUP_FILE=$BACKUP_DIRECTORY/${DATETIME}_${DB}.sql
if [ ! -n "${DB}_PW"  ]
then
  PASSWORD=$(eval echo \$${DB}_PW)
  USER=$(eval echo \$${DB}_USER)
  /usr/bin/mysqldump --single-transaction -v --user $USER --password $PASSWORD -h localhost -r $BACKUP_FILE $DB
else
  /usr/bin/mysqldump --single-transaction -v --user $USER -h localhost -r $BACKUP_FILE $DB
fi
/bin/gzip $BACKUP_FILE
$S3_CMD put ${BACKUP_FILE}.gz $S3_BUCKET_URL
done
