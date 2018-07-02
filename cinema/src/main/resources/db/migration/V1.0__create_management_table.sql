create table if not exists management(
	`login` TXT NOT NULL UNIQUE, 
	`password` TXT
);
