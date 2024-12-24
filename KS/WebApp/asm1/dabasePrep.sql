-- create the database starting spring boot

use master

IF EXISTS (SELECT 1
FROM sys.databases
WHERE name = 'asm1byphongpthe182589')
BEGIN
    ALTER DATABASE asm1byphongpthe182589 SET SINGLE_USER WITH ROLLBACK IMMEDIATE
    DROP DATABASE asm1byphongpthe182589
END
go

CREATE DATABASE asm1byphongpthe182589
GO

USE asm1byphongpthe182589
GO

/*

use the following accounts
noAuth:password123 - no auth at all
userAuth:password123 - has user perms
disabled:password123 - has user perms but is disabled
AdminA1:adminpassword - has admin & user perms

use asm1byphongpthe182589

select * from employee
select * from account
select * from account_permission
select * from permission
select * from permission_endpoint
select * from department

*/