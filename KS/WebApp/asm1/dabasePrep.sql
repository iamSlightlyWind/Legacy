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

CREATE TABLE employee
(
    id INT PRIMARY KEY,
    name NVARCHAR(50)
)
GO

CREATE TABLE account
(
    id INT PRIMARY KEY,
    username NVARCHAR(50),
    password NVARCHAR(50)
)
go

/*

select * from employee
select * from account

*/