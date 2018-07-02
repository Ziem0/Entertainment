create table if not exists `worker`(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT,
	`name` TXT,
	`login` TXT NOT NULL UNIQUE,
	`password` TXT
);
