class pagespeed {

    # This package depends on apache module being installed
    package { "pagespeed":
      ensure    =>  installed,
      provider  =>  dpkg,
      source    =>  "/var/www/third_party/pagespeed/mod-pagespeed-stable_current_amd64.deb",
    }

    exec {
        "copy-custom-pagespeed-conf-mods-enabled":
        path => ["/bin", "/usr/bin"],
        command => "sudo cp /var/www/third_party/pagespeed/pagespeed.conf /etc/apache2/mods-enabled/",
    }

    exec {
        "copy-custom-pagespeed-conf-mods-available":
        path => ["/bin", "/usr/bin"],
        command => "sudo cp /var/www/third_party/pagespeed/pagespeed.conf /etc/apache2/mods-available/",
    }


      #Set perms for DJANGO virtual environment.
    file { "cache_mod_pagespeed":
            path => "/var/cache/mod_pagespeed",
            mode => 0777,
            owner => jenkins,
            group => root,
    }

  	exec {
		"restart_apache_for_modspeed":
		command => "/etc/init.d/apache2 reload"
	}
	#This shall explain the dependency flow
	Package['pagespeed'] -> Exec['copy-custom-pagespeed-conf-mods-enabled'] -> Exec['copy-custom-pagespeed-conf-mods-available'] -> File['cache_mod_pagespeed'] -> Exec['restart_apache_for_modspeed']

}
