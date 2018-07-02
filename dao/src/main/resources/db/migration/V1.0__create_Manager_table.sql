create table if not exists manager(
	`login` TXT NOT NULL UNIQUE,
	`password` TXT NOT NULL
);
