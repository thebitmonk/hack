# This class will install imagemagick and convert all images to interlaced versions of themselves. This ensures that on low bandwidth connection users have better experience
class s3cmd {
	package {
		's3cmd': ensure => installed, provider => apt;
      	'gzip': ensure => installed, provider => apt;
		'tar': ensure => installed, provider => apt;
    }

    file { "s3cmd":
        path => "/root/.s3cfg",
        owner   => 'root',
		group   => 'root',
		mode    => '0755',
        content => template("s3cmd/s3cmd.erb"),
        ensure => present
    }

	Package['s3cmd'] -> Package['gzip'] -> Package['tar'] -> File['s3cmd']
}
