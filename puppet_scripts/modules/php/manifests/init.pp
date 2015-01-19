class php {
        # To run apt-get update
        exec { "php-apt-update":
	        command => "/usr/bin/apt-get update"
        }

	package {
		'php5' : ensure => installed, provider => apt;
		'php5-curl' : ensure => installed, provider => apt;
		'php5-mysql' : ensure => installed, provider => apt;
		'libapache2-mod-php5' : ensure => installed, provider => apt;
		'sed': ensure => installed, provider => apt;
	}



	
	#This shall explain the dependency flow
	Exec['php-apt-update'] -> Package['php5'] -> Package['php5-mysql'] -> Package['libapache2-mod-php5'] -> Package['sed']

}
