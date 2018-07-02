create table if not exists client(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT,
	`name` TXT NOT NULL,
	`email` TXT,
	`phone` TXT NOT NULL UNIQUE,
	`login` TXT NOT NULL UNIQUE,
	`password` TXT NOT NULL
);
