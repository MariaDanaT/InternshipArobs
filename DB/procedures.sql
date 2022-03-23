-- add column mail for People table
DROP PROCEDURE IF EXISTS addNewColumnMail;
DELIMITER //
CREATE PROCEDURE library.addNewColumnMail (
	IN  tableName VARCHAR(25),
	IN  columnName VARCHAR(25)
)
BEGIN
	DECLARE stmt VARCHAR(1024);
    SET @addColumn = CONCAT(" ALTER TABLE ",tableName," ADD COLUMN ",columnName," VARCHAR(20)");
    PREPARE stmt FROM @addColumn;
    EXECUTE stmt;
END//
DELIMITER ;

SET @tableName = 'people';
SET @columnName = 'mail';
CALL addNewColumnMail(@tableName,@columnName); 

-- add column latest update for a table row
DROP PROCEDURE IF EXISTS addColumnLatestUpdate;
DELIMITER //
CREATE PROCEDURE addColumnLatestUpdate (
	IN  tableName VARCHAR(25)
)
BEGIN
	DECLARE stmt VARCHAR(1024);
    SET @addColumn = CONCAT(" ALTER TABLE ",tableName," ADD COLUMN latestUpdate timestamp DEFAULT CURRENT_TIMESTAMP");
    PREPARE stmt FROM @addColumn;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END//
DELIMITER ;
CALL addColumnLatestUpdate("people");
-- alter table people
-- drop column latestUpdate

-- update column mail for a person
DROP PROCEDURE IF EXISTS updateMail;
DELIMITER //
CREATE PROCEDURE updateMail(
	IN  personID INT,
	IN  mail VARCHAR(25)
)
BEGIN
	UPDATE People
	SET mail = mail WHERE ID = personID;
END//
DELIMITER ;

CALL updateMail(1,"ana@yahoo.com"); 
CALL updateMail(2,"mihai.Pop@gmail.com"); 