create table if not exists movie(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT, 
	`title` TXT NOT NULL,
	`description` TXT,
	`date` TXT,
	`hour` TXT,
	`hall_ID` INTEGER,
	FOREIGN KEY(`hall_ID`) REFERENCES `hall`(`id`) ON DELETE CASCADE
);
