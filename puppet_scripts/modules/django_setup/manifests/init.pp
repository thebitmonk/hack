# This class container django specific setup, executed after all other infrastructure setup

class django_setup {
	
	exec {
		"install_requirements": 
		command => "/bin/bash -c 'source ../DJANGO/bin/activate ; pip install -r requirements.txt'",
		cwd => "/var/www/server",
		timeout => 1200, # Should install requirements in 20 min on slow connection too.
	}

	django_setup::mysql_conf{'dhinchak': password => "mysql", db_user => "root" }
	#Creating sendy database for email. Later may have to separate it out
	django_setup::mysql_conf{'sendy': password => "mysql", db_user => "root" }
	
	exec {
		"syncdb":
		command => "/bin/bash -c 'source ../DJANGO/bin/activate ; python manage.py syncdb --noinput'",
		cwd => "/var/www/server"
	}

	exec {
		"migrate":
		command => "/bin/bash -c 'source ../DJANGO/bin/activate ; python manage.py migrate --all --noinput'",
		cwd => "/var/www/server"
	}

	exec {
		"load_fixtures":
		command => "/bin/bash -c 'source ../DJANGO/bin/activate ; python manage.py loaddata fixtures/initial_data.json'",
		cwd => "/var/www/server"
	}


	exec {
	        "enable_modrewrite":
	         command => "/usr/sbin/a2enmod rewrite"
	}

	exec {
		"restart_apache":
		command => "/etc/init.d/apache2 reload"
	}


	exec { "start_varnish":
		command => "/etc/init.d/varnish restart",
	}

	Exec['install_requirements'] ->  Django_setup::Mysql_conf['dhinchak'] -> Django_setup::Mysql_conf['sendy'] ->  Exec['syncdb'] -> Exec['migrate'] -> Exec['load_fixtures']  -> Exec['enable_modrewrite'] -> Exec['restart_apache'] -> Exec['start_varnish']
}
