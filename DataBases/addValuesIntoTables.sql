INSERT INTO People (name, surname, birthDate)    
VALUES ( "Ana","Popescu", '2001-12-20');   
INSERT INTO People (name, surname, birthDate)    
VALUES ( "Mihai","Pop", '1998-12-20'); 
INSERT INTO People (name, surname, birthDate)    
VALUES ( "Ion","Tomescu", '1993-10-29'); 
INSERT INTO People (name, surname, birthDate)    
VALUES ( "Mia","Anton", '2001-03-29'); 

INSERT INTO Cards (personID, start, validity)    
VALUES (1, '2020-10-10', '2022-10-10'); 
INSERT INTO Cards (personID, start, validity)    
VALUES (2, '2021-08-12', '2022-08-12');
INSERT INTO Cards (personID, start, validity)    
VALUES (3, '2022-02-12', '2023-02-12');

INSERT INTO Books (title,type)    
VALUES ('Good Omens', 'novel');   
INSERT INTO Books (title,type)    
VALUES ('The Explorers Guild', 'fiction');

INSERT INTO Authors (name)    
VALUES ('Kevin Costner');   
INSERT INTO Authors (name)    
VALUES ('Jonathan Baird');   
INSERT INTO Authors (name)    
VALUES ('Neil Gaiman');  
INSERT INTO Authors (name)    
VALUES ('Terry Pratchett');

INSERT INTO AuthorsBook (authorID, bookID)    
VALUES (1,2);
INSERT INTO AuthorsBook (authorID, bookID)    
VALUES (2,2);
INSERT INTO AuthorsBook (authorID, bookID)    
VALUES (3,1);
INSERT INTO AuthorsBook (authorID, bookID)    
VALUES (4,1);

INSERT INTO Borrowings (personID, bookID, borrowDate)    
VALUES (1,1,'2022-02-20');
INSERT INTO Borrowings (personID, bookID, borrowDate)    
VALUES (3,2,'2022-03-02');
INSERT INTO Borrowings (personID, bookID, borrowDate)    
VALUES (2,1,'2022-03-20');