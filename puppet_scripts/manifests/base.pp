
node default {
	include common
	include mysql
	include apache_vhosts
	include varnish
	include django_setup
	include pagespeed
    	include imagemagick
	include sass
	include php
    	include s3cmd
    	include backup

	Class['common'] -> Class['mysql'] -> Class['apache_vhosts'] -> Class['varnish'] -> Class['django_setup'] -> Class['pagespeed'] -> Class['imagemagick'] -> Class['sass'] -> Class['php'] -> Class['s3cmd'] -> Class['backup']
}
