create table if not exists bookedSeat(
	`id` INTEGER PRIMARY KEY AUTOINCREMENT,
	`movie_ID` INTEGER NOT NULL,
	`client_ID` INTEGER NOT NULL,
	FOREIGN KEY(`movie_ID`) REFERENCES `movie`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`client_ID`) REFERENCES `client`(`id`) ON DELETE CASCADE
);
