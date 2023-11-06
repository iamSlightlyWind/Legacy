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
	SubjectID INT REFERENCES Subject(id),
    AssessmentID INT identity(1,1) PRIMARY KEY,
    Category nvarchar(50) not null,
    Weight float not null
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
    StudentCode nvarchar(15)
)

create table Enrollment
(
    StudentID int references student(StudentID),
    SubjectID int references subject(id),
    Status nvarchar(50) -- Not Passed, Attendance Fail, Passed
)

create table studentGrade
(
    StudentID int references student(StudentID),
    AssessmentID int references Assessment(assessmentID),
    Grade float
)
go

create trigger trg_EnrollStudent -- when enroll student, insert studentID and all assessmentid into studentGrade table
on Enrollment
after insert
as
begin
    declare @studentID int, @subjectID int

    select @studentID = StudentID, @subjectID = SubjectID
    from inserted

    insert into studentGrade (StudentID, AssessmentID) 
    select @studentID, AssessmentID
    from Assessment
    where SubjectID = @subjectID
end
go

CREATE TRIGGER trg_InsertStudentCode
ON Student
AFTER INSERT
AS
BEGIN
    DECLARE @id INT, @firstName NVARCHAR(50), @lastName NVARCHAR(50), @studentCode NVARCHAR(15)

    SELECT @id = StudentID, @firstName = firstName, @lastName = lastName FROM inserted

    SET @studentCode = CONCAT(@firstName, UPPER(SUBSTRING(@lastName, 1, 1)), UPPER(SUBSTRING(@lastName, CHARINDEX(' ', @lastName) + 1, 1)), FORMAT(@id, '0000'))

    UPDATE Student SET StudentCode = @studentCode WHERE StudentID = @id
END
go

create procedure insertAssessment -- subjectID, count, category, weight
(
    @subjectID int,
    @count int,
    @category nvarchar(50),
    @weight float
)
as
begin
    declare @percent float;
    select @percent = @weight / @count

    while @count > 0
    begin
        insert into Assessment (subjectID, category, weight) values (@subjectID, @category, @percent)
        set @count = @count - 1
    end
end
go

---------- Insert into subject table ----------

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
go
---------- Insert into student table ----------

INSERT INTO Student (FirstName, LastName) VALUES ('Tuan', 'Nguyen Van')
insert into Student (FirstName, LastName) values ('Minh', 'Tran Thanh')
insert into Student (FirstName, LastName) values ('Huong', 'Le Thi')
insert into Student (FirstName, LastName) values ('Duc', 'Pham Hoang')
insert into Student (FirstName, LastName) values ('Hai', 'Vo Dinh')
insert into Student (FirstName, LastName) values ('Thu', 'Doan Anh')
insert into Student (FirstName, LastName) values ('Nga', 'Hoang Thi')
insert into Student (FirstName, LastName) values ('Thao', 'Ngo Van')
insert into Student (FirstName, LastName) values ('Hoa', 'Vu Thanh')
insert into Student (FirstName, LastName) values ('Quoc', 'Nguyen Anh')
insert into Student (FirstName, LastName) values ('Linh', 'Phan Thi')
insert into Student (FirstName, LastName) values ('Dat', 'Hoang Van')
insert into Student (FirstName, LastName) values ('An', 'Le Van')
insert into Student (FirstName, LastName) values ('Phuong', 'Truong Minh')
insert into Student (FirstName, LastName) values ('Khanh', 'Bui Van')
insert into Student (FirstName, LastName) values ('Cuong', 'Nguyen Thanh')
insert into Student (FirstName, LastName) values ('Nhi', 'Pham Thi')
insert into Student (FirstName, LastName) values ('Thang', 'Le Van')
insert into Student (FirstName, LastName) values ('Mai', 'Tran Thi')
insert into Student (FirstName, LastName) values ('Son', 'Nguyen Trong')
insert into Student (FirstName, LastName) values ('Trang', 'Hoang Van')
insert into Student (FirstName, LastName) values ('Phuc', 'Nguyen Cong')
insert into Student (FirstName, LastName) values ('Lan', 'Tran Thi')
insert into Student (FirstName, LastName) values ('Hung', 'Le Van')
insert into Student (FirstName, LastName) values ('Nhung', 'Pham Thi')
insert into Student (FirstName, LastName) values ('Tien', 'Vo Van')
insert into Student (FirstName, LastName) values ('Kieu', 'Nguyen Thanh')
insert into Student (FirstName, LastName) values ('Hieu', 'Bui Van')
insert into Student (FirstName, LastName) values ('Giang', 'Doan Thi')
insert into Student (FirstName, LastName) values ('Dinh', 'Luong Van')
insert into Student (FirstName, LastName) values ('Anh', 'Truong Thi')
insert into Student (FirstName, LastName) values ('Thuan', 'Nguyen Van')
insert into Student (FirstName, LastName) values ('Quyen', 'Le Thi')
insert into Student (FirstName, LastName) values ('Tung', 'Phan Van')
insert into Student (FirstName, LastName) values ('Tam', 'Nguyen Thi')
insert into Student (FirstName, LastName) values ('Van', 'Hoang Thi')
insert into Student (FirstName, LastName) values ('Quang', 'Doan Van')
insert into Student (FirstName, LastName) values ('Thi', 'Nguyen Van')
insert into Student (FirstName, LastName) values ('Long', 'Nguyen Minh')
insert into Student (FirstName, LastName) values ('Thuy', 'Nguyen Thi')
go
---------- Insert into Assessment table ----------

exec insertAssessment 9982, 2, 'Group presentation', 0.1
exec insertAssessment 9982, 2, 'Lab', 0.2
exec insertAssessment 9982, 3, 'Progress test', 0.3
exec insertAssessment 9982, 1, 'Final exam', 0.4

exec insertAssessment 9569, 2, 'Progress test', 0.1
exec insertAssessment 9569, 1, 'Assignment', 0.1
exec insertAssessment 9569, 5, 'Workshop', 0.1
exec insertAssessment 9569, 1, 'Practical Exam', 0.4
exec insertAssessment 9569, 1, 'Final exam', 0.3

exec insertAssessment 10326, 3, 'Assignment/Exercises', 0.3
exec insertAssessment 10326, 3, 'Progress Test', 0.3
exec insertAssessment 10326, 1, 'Final Exam', 0.4

exec insertAssessment 10014, 2, 'Assignment', 0.3
exec insertAssessment 10014, 4, 'Exercises', 0.3
exec insertAssessment 10014, 1, 'Final exam', 0.4

exec insertAssessment 10085, 1, 'Theoretical Exam', 1

exec insertAssessment 9241, 3, 'Progress Test', 0.3
exec insertAssessment 9241, 3, 'Assignments/Exercises', 0.3
exec insertAssessment 9241, 1, 'Final Exam', 0.4

exec insertAssessment 10114, 4, 'Lab', 0.2
exec insertAssessment 10114, 1, 'Presentation', 0.2
exec insertAssessment 10114, 2, 'Progress test', 0.2
exec insertAssessment 10114, 1, 'Final exam', 0.4

exec insertAssessment 10173, 1, 'Practical Exam', 0.5
exec insertAssessment 10173, 1, 'Theoretical Exam', 0.5

exec insertAssessment 10084, 3, 'Activity', 0.15
exec insertAssessment 10084, 2, 'Group Assignment', 0.2
exec insertAssessment 10084, 3, 'Group Project', 0.3
exec insertAssessment 10084, 1, 'Participation', 0.1
exec insertAssessment 10084, 1, 'Quiz', 0.05
exec insertAssessment 10084, 1, 'Final exam', 0.2

exec insertAssessment 10296, 1, 'Assignment', 0.2
exec insertAssessment 10296, 6, 'Lab', 0.1
exec insertAssessment 10296, 1, 'Practical Exam', 0.3
exec insertAssessment 10296, 2, 'Progress Test', 0.1
exec insertAssessment 10296, 1, 'Final exam', 0.3

exec insertAssessment 9226, 2, 'Small Test', 0.2
exec insertAssessment 9226, 1, 'Participation', 0.1
exec insertAssessment 9226, 1, 'Mid-term', 0.3
exec insertAssessment 9226, 1, 'Practical exam', 0.3
exec insertAssessment 9226, 1, 'Practical exam', 0.1

exec insertAssessment 10015, 2, 'Progress test', 0.2
exec insertAssessment 10015, 2, 'Assignment', 0.2
exec insertAssessment 10015, 1, 'Practical Exam', 0.3
exec insertAssessment 10015, 1, 'Final exam', 0.3

exec insertAssessment 10010, 1, 'Assignment', 0.2
exec insertAssessment 10010, 5, 'Lab', 0.1
exec insertAssessment 10010, 1, 'Practical Exam', 0.3
exec insertAssessment 10010, 2, 'Progress test', 0.1
exec insertAssessment 10010, 1, 'Final exam', 0.3

exec insertAssessment 9966, 1, 'Practical Exam', 0.5
exec insertAssessment 9966, 1, 'Theoretical Exam', 0.5

exec insertAssessment 9243, 2, 'Assignment', 0.2
exec insertAssessment 9243, 1, 'Computer Project', 0.15
exec insertAssessment 9243, 3, 'Progress Test', 0.3
exec insertAssessment 9243, 1, 'Final exam', 0.35

exec insertAssessment 9228, 2, 'Small Test', 0.2
exec insertAssessment 9228, 1, 'Participation', 0.1
exec insertAssessment 9228, 1, 'Mid-term', 0.3
exec insertAssessment 9228, 1, 'Practical exam', 0.3
exec insertAssessment 9228, 1, 'Practical exam', 0.1

exec insertAssessment 10031, 1, 'Active learning', 0.1
exec insertAssessment 10031, 1, 'Presentation', 0.1
exec insertAssessment 10031, 2, 'Progress test', 0.1
exec insertAssessment 10031, 1, 'Project', 0.3
exec insertAssessment 10031, 1, 'Final exam', 0.4

exec insertAssessment 10032, 6, 'On-going Assessment', 0.6
exec insertAssessment 10032, 1, 'Final Project Presentation', 0.4

exec insertAssessment 10033, 15, 'Lab', 0.4
exec insertAssessment 10033, 1, 'Midterm test', 0.2
exec insertAssessment 10033, 1, 'Participation in Discussions', 0.1
exec insertAssessment 10033, 1, 'Final exam', 0.3

exec insertAssessment 10021, 1, 'Lab', 1
go