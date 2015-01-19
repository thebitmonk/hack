# 
# Adds and enables virtual hosts. Sets up /var/www symlinks.
#

class apache_vhosts {
  	apache_vhosts::vhost { ['django-dhinchak']: }
}
