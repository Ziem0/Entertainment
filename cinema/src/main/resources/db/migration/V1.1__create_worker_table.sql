create table if not exists `worker`(
	`id` INTEGER PRIMARY KEY UNIQUE,
	`name` TXT,
	`login` TXT,
	`password` TXT
);
