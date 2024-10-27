use master
go

IF EXISTS (SELECT 1
FROM sys.databases
WHERE name = 'hibernate_test')
BEGIN
    ALTER DATABASE hibernate_test SET SINGLE_USER WITH ROLLBACK IMMEDIATE
    DROP DATABASE hibernate_test
END
go

create database hibernate_test;
go

use hibernate_test;
go

create table EMPLOYEE (
   id INT NOT NULL identity,
   first_name VARCHAR(20) default NULL,
   last_name  VARCHAR(20) default NULL,
   salary     INT  default NULL,
   PRIMARY KEY (id)
);