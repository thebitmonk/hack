# This class will install imagemagick and convert all images to interlaced versions of themselves. This ensures that on low bandwidth connection users have better experience
class imagemagick {
	package {
		'imagemagick': ensure => installed, provider => apt;
	}

	file { '/tmp/interlace.sh':
		ensure  => 'file',
  		source  => 'puppet:///modules/imagemagick/interlace.sh',
  		group   => 'root',
   		mode    => '755',
  		owner   => 'jenkins',
    }

    exec { "/tmp/interlace.sh":
        	require => File['/tmp/interlace.sh'],
        	cwd => '/',
        	path => '/sbin/:/usr/bin/:/bin',
    }
    
	Package['imagemagick'] -> Exec['/tmp/interlace.sh']
}
