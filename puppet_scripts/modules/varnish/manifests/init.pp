#This class will install varnish HTTP accelerator

class varnish {
	
	# To run apt-get update
	exec { "apt-update2":
		command => "/usr/bin/apt-get update"
	}

    package {"varnish":
        ensure => "installed"
    }
 
    file {'/etc/apache2/ports.conf':
        ensure => file,
        require => Package['apache2'],
        content => template('varnish/ports.conf.erb'),
    }
 
    file {'/etc/default/varnish':
        ensure => file,
        require => Package['varnish'],
        content => template('varnish/varnish.erb'),
    }

    Exec['apt-update'] -> Package['varnish'] -> File['/etc/apache2/ports.conf'] -> File['/etc/default/varnish']

}

