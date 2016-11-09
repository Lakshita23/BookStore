drop table BookOrder
drop table Rating
drop table Feedback
drop table Book
drop table Customer

CREATE TABLE Book (
	ISBN int NOT NULL, 
	title varchar(255) NOT NULL, 
	author varchar(255) NOT NULL, 
	publisher varchar(255) NOT NULL, 
	year_published int NOT NULL, 
	copies int NOT NULL DEFAULT 0,
	price float NOT NULL DEFAULT 0.0,
	book_format varchar(9) NOT NULL CHECK (book_format = 'hardcover' or book_format = 'softcover'), 
	keywords varchar(100) NOT NULL, 
	subject varchar(100) NOT NULL, 
	primary key (ISBN)
);


CREATE TABLE Customer (
	name varchar(255) NOT NULL,
	login varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	credit_card int NOT NULL,
	address varchar(255) NOT NULL,
	phone int,
	primary key(login)
);


CREATE TABLE Feedback(
	feedbackID int NOT NULL,
	customer varchar(255) NOT NULL,
	feedbackBook int NOT NULL,
	feedback_text varchar(255) NOT NULL,
	score int CHECK (score >=1 or score <= 10),
	PRIMARY KEY (feedbackID),
	FOREIGN KEY (customer) REFERENCES Customer(login),
	FOREIGN KEY (feedbackBook) REFERENCES Book(ISBN)
);

CREATE TABLE BookOrder(
	book int NOT NULL,
	customer varchar(255),
	order_ID int NOT NULL auto_increment,
	order_date date,
	order_status varchar(255) NOT NULL,
	copies int,
	PRIMARY KEY (order_ID),
	FOREIGN KEY (book) REFERENCES Book(ISBN),
	FOREIGN KEY (customer) REFERENCES Customer(login)
);

CREATE TABLE Rating(
	rating_ID int NOT NULL,
	feedbackID int NOT NULL,
    customer varchar(255) NOT NULL,
    rate varchar(20) check (rate = 'useless' or rate = 'useful' or rate = 'very useful'),
    PRIMARY KEY (rating_ID),
    FOREIGN KEY (customer) REFERENCES Customer(login),
	FOREIGN KEY (feedbackID) REFERENCES Feedback(feedbackID)
);