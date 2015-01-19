#
# Adds and enables an Apache virtual host
#
define apache_vhosts::vhost() {

  # First purge the directory
  file { 
    "/etc/apache2/sites-enabled":
    ensure  => 'directory',
    recurse => true,
    purge   => true,
  }

  # First add vhost file to sites available
  file {
    "/etc/apache2/sites-available/${name}":
      source => "puppet:///modules/apache_vhosts/${name}",
      require => Package['apache2'],
  }

  # Then add symlink in sites-enabled
  file {
    "/etc/apache2/sites-enabled/${name}":
      ensure => link,
      target => "/etc/apache2/sites-available/${name}",
  }
}
