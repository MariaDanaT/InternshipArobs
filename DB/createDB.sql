CREATE DATABASE Library;
USE Library;

CREATE TABLE People(
ID int NOT NULL AUTO_INCREMENT,
name varchar(20),
surname varchar(20),
birthDate date,
PRIMARY KEY(ID)
);

CREATE TABLE Cards(
ID int NOT NULL AUTO_INCREMENT,
personID int NOT NULL, 
start date,
validity date,
FOREIGN KEY (personID) REFERENCES People(ID),
PRIMARY KEY(ID)
);

CREATE TABLE Books(
ID int NOT NULL AUTO_INCREMENT,
title varchar(50),
type varchar(30),
PRIMARY KEY(ID)
);


CREATE TABLE Borrowings(
ID int NOT NULL AUTO_INCREMENT,
personID int NOT NULL,
bookID int NOT NULL,
borrowDate date,
FOREIGN KEY (personID) REFERENCES People(ID),
FOREIGN KEY (bookID) REFERENCES Books(ID),
PRIMARY KEY(ID)
);


CREATE TABLE Authors(
ID int NOT NULL AUTO_INCREMENT,
name varchar(80),
PRIMARY KEY(ID)
);

CREATE TABLE AuthorsBook(
ID int NOT NULL AUTO_INCREMENT,
authorID int NOT NULL,
bookID int NOT NULL, 
FOREIGN KEY (authorID) REFERENCES Authors(ID),
FOREIGN KEY (bookID) REFERENCES Books(ID),
PRIMARY KEY(ID)
);

