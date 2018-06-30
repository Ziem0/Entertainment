create table if not exists bookedSeat(
	`id` INTEGER PRIMARY KEY NOT NULL UNIQUE,
	`movieID` INTEGER,
	`column` TXT,
	`row` INTEGER,
	`customerID` INTEGER,
	FOREIGN KEY(`movieID`) REFERENCES `movie`(`id`) on DELETE CASCADE,
	FOREIGN KEY(`customerID`) REFERENCES `customer`(`id`) on DELETE CASCADE
);
