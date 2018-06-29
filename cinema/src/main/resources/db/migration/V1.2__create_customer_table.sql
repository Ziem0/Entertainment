create table if not exists customer(
	`id` INTEGER PRIMARY KEY NOT NULL UNIQUE AUTOINCREMENT,
	`name` TXT,
	`email` TXT,
	`phone` INTEGER,
	`login` TXT NOT NULL UNIQUE,
	`password` TXT NOT NULL
);
