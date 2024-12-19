-- this is to change the sa account password without policy check, since smss doesnt exist on linux
ALTER LOGIN [sa] WITH PASSWORD='123' , CHECK_POLICY = OFF

/*
use master

IF EXISTS (SELECT 1
FROM sys.databases
WHERE name = 'asm1byphongpthe182589')
BEGIN
    ALTER DATABASE asm1byphongpthe182589 SET SINGLE_USER WITH ROLLBACK IMMEDIATE
    DROP DATABASE asm1byphongpthe182589
END
*/