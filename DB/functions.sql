DROP FUNCTION IF EXISTS numberOfBorrowedBooksByAPerson;
DELIMITER $$  
CREATE FUNCTION numberOfBorrowedBooksByAPerson(  
     id INT
)   
RETURNS INT  
DETERMINISTIC  
BEGIN  
    DECLARE counter INT;  
     SELECT COUNT(*) INTO counter FROM borrowings b
     WHERE b.personID = id;
    RETURN (counter);  
END$$  
DELIMITER ;

SET @total= numberOfBorrowedBooksByAPerson(1);
SELECT @total;  

SET @total= numberOfBorrowedBooksByAPerson(4);
SELECT @total;  