
class backup {

	file { '/tmp/backup.sh':
		ensure  => 'file',
  		source  => 'puppet:///modules/backup/backup.sh',
  		group   => 'root',
   		mode    => '777',
  		owner   => 'root',
    }

    cron { 'mysql_backup_s3':
      command => "/tmp/backup.sh onetime > /dev/null",
      user => 'root',
      require => File['/tmp/backup.sh'],
      minute => 1,
      hour => ['0-23'],
    }

    File['/tmp/backup.sh'] -> Cron['mysql_backup_s3']

}
