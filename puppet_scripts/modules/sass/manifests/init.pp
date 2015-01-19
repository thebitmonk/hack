#This installs sass which converts .scss file to its corresponding css file

class sass {
	package {
		'build-essential': ensure => installed, provider => apt;
		'ruby-full': ensure => installed, provider => apt;
		'rubygems': ensure => installed, provider => apt;
	}	

	package {
		'sass': ensure => installed, provider => gem;
	}

	exec { "scss-convert-css":
		cwd => '/var/www/server/static/css',
		path => "/usr/local/bin:/usr/bin:/bin",
		command => "sass --watch base.scss:base.css &",
	}

	file { "base-css":
		path => '/var/www/server/static/css/base.css',
		mode => 0755,
		owner => jenkins,
		group => root,
	}

	Package['build-essential'] -> Package['ruby-full'] -> Package['rubygems'] -> Package['sass'] -> Exec['scss-convert-css'] -> File['base-css']

}
