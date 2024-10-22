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

-- SELECT practice,gender,phone,name,student_id,id,email,theory FROM student WHERE id = ?
-- Long id, String name, String gender, String phone, String email, String studentId, Double theory, Double practice

create table Student
(
    id float primary key,
    name varchar(255),
    gender varchar(255),
    phone varchar(255),
    email varchar(255),
    student_id varchar(255),
    theory float,
    practice float
)

-- gender,phone,subsidy,name,basic_salary,id,email FROM teacher_table WHERE id = ?
-- Long id, String name, String gender, String phone, String email, double basicSalary, double subsidy

create table teacher_table
(
    id float primary key,
    name varchar(255),
    gender varchar(255),
    phone varchar(255),
    email varchar(255),
    basic_salary float,
    subsidy float
)

-- Insert records into Student table
insert into Student (id, name, gender, phone, email, student_id, theory, practice) values
(1, 'Alice', 'Female', '123-456-7890', 'alice@example.com', 'S001', 85.5, 90.0),
(2, 'Bob', 'Male', '234-567-8901', 'bob@example.com', 'S002', 78.0, 82.5),
(3, 'Charlie', 'Male', '345-678-9012', 'charlie@example.com', 'S003', 92.0, 88.0),
(4, 'Diana', 'Female', '456-789-0123', 'diana@example.com', 'S004', 80.0, 85.0),
(5, 'Eve', 'Female', '567-890-1234', 'eve@example.com', 'S005', 88.5, 91.0);

-- Insert records into Teacher table
insert into teacher_table (id, name, gender, phone, email, basic_salary, subsidy) values
(1, 'Mr. Smith', 'Male', '678-901-2345', 'smith@example.com', 50000.0, 5000.0),
(2, 'Ms. Johnson', 'Female', '789-012-3456', 'johnson@example.com', 52000.0, 5200.0),
(3, 'Mr. Brown', 'Male', '890-123-4567', 'brown@example.com', 51000.0, 5100.0),
(4, 'Ms. Davis', 'Female', '901-234-5678', 'davis@example.com', 53000.0, 5300.0),
(5, 'Mr. Wilson', 'Male', '012-345-6789', 'wilson@example.com', 54000.0, 5400.0);

SELECT * FROM Student;
SELECT * FROM teacher_table;

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