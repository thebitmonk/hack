# This class contains all the dependencies needed to setup the machine for Web Application with Django

class common {

	# To run apt-get update
	exec { "apt-update":
    	command => "/usr/bin/apt-get update"
	}

    # This Ensures the jenkins user exist or not and if not then creates the user.
    user { "jenkins":
    		ensure => present,
    		home  => '/home/jenkins',
    		shell => "/bin/bash",
    		groups => ["root", "admin"],
    		managehome => true,
    }

    # This will make sure that sudoers file is added to /etc/sudoers.d/ for password less root access for jenkins user
    file { "/etc/sudoers.d/dhinchak-jenkins-user":
    		ensure => present,
    		owner => root,
    		group => root,
    		mode => 0440,
    		content => "jenkins ALL=(ALL) NOPASSWD:ALL\n",
    }

    # This includes the packages required.
    package {
        'curl' : ensure => installed, provider => apt;
        'python2.7': ensure => installed, provider => apt;
        'python-setuptools': ensure => installed, provider => apt;
        'virtualenv': ensure => "1.10.1", provider => pip;
        'apache2': ensure => installed, provider => apt;
        'libapache2-mod-wsgi': ensure => installed, provider => apt;
        'python2.7-dev': ensure => installed, provider => apt;
    }

	# This installs the pip.
	exec { "easy_install-2.7 pip==1.4.1":
			path => "/usr/local/bin:/usr/bin:/bin";
	}

    #Add varnish-cache repo
    exec { "add-varnish-cache-repo":
        path => "/usr/bin",
        command => "curl http://repo.varnish-cache.org/debian/GPG-key.txt | sudo apt-key add -"
    }

    #Add Varnish-Cache repo to sources.list
    exec { "add-varnish-cache-to-sources":
        path => "/bin",
        command => "echo \"deb http://repo.varnish-cache.org/ubuntu/ precise varnish-3.0\" | /usr/bin/tee -a /etc/apt/sources.list"
    }

  	#Creates the directory www and set perms if not there.
  	file { "www":
  			path => "/var/www",
          	ensure => directory, 
           	mode => 0755, 
           	owner => root, 
            group => root,
    }

    # This installs the virtualenv.
    exec { "virtualenv":
            command => "virtualenv --no-site-packages /var/www/DJANGO",
            path => "/usr/local/bin:/usr/bin:/bin",
            unless => "[ -f '/var/www/DJANGO/bin/activate' ]",
    }

    #Set perms for DJANGO virtual environment.
    file { "www-DJANGO":
            path => "/var/www/DJANGO",
            mode => 0755, 
            owner => jenkins, 
            group => root,
    }

    #This explains the flow how dependencies should get installed.
    Exec['apt-update'] -> Package['curl'] -> User['jenkins'] -> File['/etc/sudoers.d/dhinchak-jenkins-user'] -> Package['python2.7'] -> Package['python-setuptools'] -> Exec['easy_install-2.7 pip==1.4.1'] -> Package['virtualenv'] -> File['www'] -> Exec['virtualenv'] -> File['www-DJANGO'] -> Package['apache2'] -> Package['libapache2-mod-wsgi'] -> Exec['add-varnish-cache-repo'] -> Exec['add-varnish-cache-to-sources']
}
