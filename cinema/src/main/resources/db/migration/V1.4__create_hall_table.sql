create table if not exists hall(
	`id` INTEGER PRIMARY KEY NOT NULL UNIQUE,
	`rowLength` INTEGER,
	`columnLength` INTEGER
);
