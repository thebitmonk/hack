# This is used to create a database

define django_setup::mysql_conf($db_user, $password) {
	
	exec { "create-${name}-db":
			unless => "/usr/bin/mysql -u${db_user} -p'${password}' ${name}",
			command => "/usr/bin/mysql -uroot -p'$password' -e \"create database ${name}; grant all on ${name}.* to '${db_user}'@localhost identified by '$password';\"",
    }
}

