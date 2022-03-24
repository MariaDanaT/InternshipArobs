START TRANSACTION; 
UPDATE people 
SET    mail = 'tomescuion@gamil.com'
WHERE  ID = 3;
COMMIT;


START TRANSACTION;
UPDATE people 
SET    mail = 'anton.mia@gamil.com'
WHERE  ID = 4;
DO sleep(12); 
ROLLBACK;

-- code below must be executed into another connection to see how dirty reads work
-- select * from people where ID = 4;
-- do sleep(10);
-- select * from people where ID = 4;
