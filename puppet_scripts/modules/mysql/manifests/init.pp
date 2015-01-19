# This class will install mysql required for django-powered web application

class mysql {

    # This includes the packages required.
    package { 
        'mysql-server-5.5': ensure => installed, provider => apt;
        'libmysqlclient-dev' : ensure => installed, provider => apt;
        'maatkit' : ensure => installed, provider => apt;
    }

    exec { 
        "set-mysql-password":
        unless => "mysqladmin -uroot -pmysql status",
        path => ["/bin", "/usr/bin"],
        command => "mysqladmin -uroot password mysql",
    }
 
   #This explains the flow how dependencies should get installed.  
   Package['mysql-server-5.5'] -> Package['libmysqlclient-dev'] -> Exec['set-mysql-password']
}

