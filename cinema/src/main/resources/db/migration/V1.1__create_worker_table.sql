create table if not exists `worker`(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name` TXT,
	`login` TXT,
	`password` TXT
);
