create table if not exists bookedSeat(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT,
	`movieID` INTEGER,
	`column` TXT,
	`row` INTEGER,
	FOREIGN KEY(`movieID`) REFERENCES `movie`(`id`) on DELETE CASCADE
);
