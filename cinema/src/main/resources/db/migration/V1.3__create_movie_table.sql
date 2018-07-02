create table if not exists movie(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT,
	`title` TXT,
	`description` TXT,
	`hallID` INTEGER,
	`date` TXT,
	`hour` TXT,
	FOREIGN KEY(`hallID`) REFERENCES `hall`(`id`) ON DELETE CASCADE
); 
