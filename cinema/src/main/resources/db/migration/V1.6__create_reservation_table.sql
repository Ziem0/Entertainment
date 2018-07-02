create table if not exists reservation(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT,
	`movieID` INTEGER NOT NULL,
	`customerID` INTEGER NOT NULL,
	FOREIGN KEY(`movieID`) REFERENCES `movie`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`customerID`) REFERENCES `customer`(`id`) ON DELETE CASCADE
);
