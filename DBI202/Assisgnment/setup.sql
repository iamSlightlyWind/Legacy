USE master;
GO
ALTER DATABASE DBI_ASM
SET SINGLE_USER
WITH ROLLBACK IMMEDIATE;
GO
DROP DATABASE DBI_ASM;
GO

create database DBI_ASM
go

use DBI_ASM
go

create table Subject
(
    ID int primary key,
    Code nvarchar(10) not null,
    Name nvarchar(50) not null,
    Credit int not null,
    Prerequisite int references subject(id)
)

CREATE TABLE Assessment
(
	AssessmentID INT identity(1,1) PRIMARY KEY,
	SubjectID INT REFERENCES Subject(id),
    ParentID INT REFERENCES Assessment(assessmentID)
)

create table AssessmentDetail
(
    SubjectID int references subject(id),
    AssessmentID int references Assessment(assessmentID),
    Category nvarchar(50) not null,
    Weight float not null,
    Type nvarchar(50) not null,
    Part int not null,
    Duration int not null,
    NoQuestion int not null,
    GradingGuide nvarchar(255) not null
)

create table Student
(
    StudentID int identity(1,1) primary key,
    FirstName nvarchar(50) not null,
    LastName nvarchar(50) not null,
)

create table StudentCode
(
    StudentID int references student(StudentID),
    StudentCode nvarchar(15) not null
)

create table Grade
(
    SubjectID int references subject(ID),
    Student int references student(StudentID),
    Assessment int references Assessment(assessmentID),
    Mark int not null,
    primary key (SubjectID, student, assessment)
)
go

-- student code = studentid + first name + first letter of each words in last name
CREATE TRIGGER trg_InsertStudentCode
ON Student
AFTER INSERT
AS
BEGIN
    DECLARE @id INT, @firstName NVARCHAR(50), @lastName NVARCHAR(50), @studentCode NVARCHAR(15)

    SELECT @id = StudentID, @firstName = firstName, @lastName = lastName FROM inserted

    SET @studentCode = CONCAT(@firstName, UPPER(SUBSTRING(@lastName, 1, 1)), UPPER(SUBSTRING(@lastName, CHARINDEX(' ', @lastName) + 1, 1)), FORMAT(@id, '0000'))

    INSERT INTO StudentCode (studentID, studentCode) VALUES (@id, @studentCode)
END

---------- Insert into subject table ----------

-- First semester

insert into Subject values (9982, 'CSI104', 'Introduction to Computer Science', 3, NULL);
insert into Subject values (9569, 'PRF192', 'Programming Fundamentals', 3, NULL);
insert into Subject values (10326, 'MAE101', 'Mathematics for Engineering', 3, NULL);
insert into Subject values (10014, 'CEA201', 'Computer Organization and Architecture', 3, NULL);
insert into Subject values (10085, 'SSL101c', 'Academic Skills for University Success', 3, NULL);

-- Second semester

insert into Subject values (9241, 'MAD101', 'Discrete mathematics', 3, NULL);
insert into Subject values (10114, 'OSG202', 'Operating System', 3, NULL);
insert into Subject values (10173, 'NWC203c', 'Computer Networking', 3, NULL);
insert into Subject values (10084, 'SSG104', 'Communication and In-Group Working Skills', 3, NULL);
insert into Subject values (10296, 'PRO192', 'Object-Oriented Programming', 3, 9569);

-- Third semester

insert into Subject values (9226, 'JPD113', 'Elementary Japanese 1- A1.1', 3, NULL);
insert into Subject values (10015, 'CSD201', 'Data Structures and Algorithm', 3, 10296);
insert into Subject values (10010, 'DBI202', 'Database Systems', 3, NULL);
insert into Subject values (10021, 'LAB211', 'OOP with Java Lab', 3, 10296);
insert into Subject values (9966, 'WED201c', 'Web Design', 3, NULL);

-- Fourth semester

insert into Subject values (9243, 'MAS291', 'Statistics & Probability', 3, 10326);
insert into Subject values (9228, 'JPD123', 'Elementary Japanese 1- A1.2', 3, 9226);
insert into Subject values (10031, 'IOT102', 'Internet of Things', 3, NULL);
insert into Subject values (10032, 'PRJ301', 'Java Web Application Development', 3, 10010);
insert into Subject values (10033, 'PRJ311', 'Java Web Application Development Lab', 3, 9569);

---------- Insert into student table ----------

insert into Student values ('Tuan', 'Nguyen Van')
insert into Student values ('Minh', 'Tran Thanh')
insert into Student values ('Huong', 'Le Thi')
insert into Student values ('Duc', 'Pham Hoang')
insert into Student values ('Hai', 'Vo Dinh')
insert into Student values ('Thu', 'Doan Anh')
insert into Student values ('Nga', 'Hoang Thi')
insert into Student values ('Thao', 'Ngo Van')
insert into Student values ('Hoa', 'Vu Thanh')
insert into Student values ('Quoc', 'Nguyen Anh')
insert into Student values ('Linh', 'Phan Thi')
insert into Student values ('Dat', 'Hoang Van')
insert into Student values ('An', 'Le Van')
insert into Student values ('Phuong', 'Truong Minh')
insert into Student values ('Khanh', 'Bui Van')
insert into Student values ('Cuong', 'Nguyen Thanh')
insert into Student values ('Nhi', 'Pham Thi')
insert into Student values ('Thang', 'Le Van')
insert into Student values ('Mai', 'Tran Thi')
insert into Student values ('Son', 'Nguyen Trong')
insert into Student values ('Trang', 'Hoang Van')
insert into Student values ('Phuc', 'Nguyen Cong')
insert into Student values ('Lan', 'Tran Thi')
insert into Student values ('Hung', 'Le Van')
insert into Student values ('Nhung', 'Pham Thi')
insert into Student values ('Tien', 'Vo Van')
insert into Student values ('Kieu', 'Nguyen Thanh')
insert into Student values ('Hieu', 'Bui Van')
insert into Student values ('Giang', 'Doan Thi')
insert into Student values ('Dinh', 'Luong Van')
insert into Student values ('Anh', 'Truong Thi')
insert into Student values ('Thuan', 'Nguyen Van')
insert into Student values ('Quyen', 'Le Thi')
insert into Student values ('Tung', 'Phan Van')
insert into Student values ('Tam', 'Nguyen Thi')
insert into Student values ('Van', 'Hoang Thi')
insert into Student values ('Quang', 'Doan Van')
insert into Student values ('Thi', 'Nguyen Van')
insert into Student values ('Long', 'Nguyen Minh')
insert into Student values ('Thuy', 'Nguyen Thi')
go

-- procedure that will insert multiple rows into the Assessment table (assessment and its children). input will be the subjectID, assessmentID and how many children to insert
CREATE PROCEDURE insertAssessment
(
    @subjectID INT,
    @children INT
)
AS
BEGIN
    DECLARE @i INT = 1
    DECLARE @parentID INT

    INSERT INTO Assessment (SubjectID) VALUES (@subjectID)
    SET @parentID = SCOPE_IDENTITY()

    WHILE @i <= @children
    BEGIN
        INSERT INTO Assessment (SubjectID, ParentID) VALUES (@subjectID, @parentID)
        SET @i = @i + 1
    END
END
GO

exec insertAssessment 9226, 1


select * from Subject
select * from Student
select * from StudentCode
select * from Assessment


select S.StudentID, StudentCode, FirstName, LastName
from Student S
join StudentCode SC on S.StudentID = SC.StudentID