use master

IF EXISTS (SELECT 1
FROM sys.databases
WHERE name = 'java_ma301')
BEGIN
    ALTER DATABASE java_ma301 SET SINGLE_USER WITH ROLLBACK IMMEDIATE
    DROP DATABASE java_ma301
END

create database java_ma301

use java_ma301

create table Person
(
    id float primary key,
    name varchar(255) not null,
    gender varchar(255) not null,
    phone varchar(255) not null,
    email varchar(255) not null
)

create table Student
(
    id varchar(255) primary key,
    person_id float not null references Person(id),
    theory float not null,
    practice float not null
)

create table Teacher
(
    id varchar(255) primary key,
    person_id float not null references Person(id),
    basicSalary float not null,
    subsidy float not null
)

insert into Person
values
    (1, 'John Doe', 'Male', '1234567890', 'doe@john.com'),
    (2, 'Jane Doe', 'Female', '1234567890', 'doe@jane.com'),
    (3, 'Alice Doe', 'Female', '1234567890', 'doe@alice.com')

insert into Student
values
    (1, 1, 90, 80),
    (2, 2, 80, 90)

insert into Teacher
values
    (3, 3, 10000, 5000)

select * from Person
select * from Student
select * from Teacher

SELECT
    text
FROM
    sys.dm_exec_query_stats AS qs
CROSS APPLY 
    sys.dm_exec_sql_text(qs.sql_handle) AS st
WHERE 
    creation_time >= DATEADD(MINUTE, -5, GETDATE())
    AND text LIKE '%student%'
ORDER BY 
    creation_time DESC;