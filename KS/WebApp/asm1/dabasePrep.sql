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
    name NVARCHAR(50)
)
GO

CREATE TABLE account
(
    password NVARCHAR(50)
)
go

create table account_permission
(
    name nvarchar(50)
)

create table permission
(
    name nvarchar(50)
)

create table permission_endpoint
(
    name nvarchar(50)
)

/*

use asm1byphongpthe182589

select * from employee
select * from account
select * from account_permission
select * from permission
select * from permission_endpoint

*/