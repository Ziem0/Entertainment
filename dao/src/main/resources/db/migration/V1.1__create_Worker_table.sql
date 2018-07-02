create table if not exists worker(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT,
	`name` TXT NOT NULL,
	`login` TXT NOT NULL UNIQUE,
	`password` TXT NOT NULL
);
