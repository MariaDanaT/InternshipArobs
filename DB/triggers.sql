DROP TRIGGER IF EXISTS afterUpdatePersonInfo;  
DELIMITER $$  
  
CREATE TRIGGER afterUpdatePersonInfo
BEFORE UPDATE  
ON people FOR EACH ROW  
BEGIN  
	SET NEW.latestUpdate = current_timestamp();
END;$$  
  
DELIMITER ;  