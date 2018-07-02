create table if not exists reservation(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT,
	`movie_ID` INTEGER NOT NULL,
	`seat_ID` INTEGER NOT NULL,
	FOREIGN KEY(`movie_ID`) REFERENCES `movie`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`seat_ID`) REFERENCES `seat`(`id`) ON DELETE CASCADE
);
