use java_ma301

create table Person
(
    id int primary key,
    name varchar(255) not null,
    gender varchar(255) not null,
    phone varchar(255) not null,
    email varchar(255) not null
)

create table Student
(
    id int primary key,
    person_id int not null,
    theory float not null,
    practice float not null
)

create table Teacher
(
    id int primary key,
    person_id int not null,
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