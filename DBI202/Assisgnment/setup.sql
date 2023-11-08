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
    SubjectID int IDENTITY(1,1) primary key,
    SubjectCode nvarchar(10) not null,
    SubjectName nvarchar(50) not null,
    SubjectCredit int not null,
    SubjectPrerequisite int references subject(SubjectID)
)

CREATE TABLE Assessment
(
	SubjectID INT REFERENCES Subject(SubjectID),
    AssessmentID INT identity(1,1) PRIMARY KEY,
    AssessmentCategory nvarchar(50) not null,
    AssessmentWeight float not null,
    AssessmentCriteria float not null
)

create table Student
(
    StudentID int identity(1,1) primary key,
    FirstName nvarchar(50) not null,
    LastName nvarchar(50) not null,
    StudentCode nvarchar(15)
)

create table StudentAssessment
(
    StudentID int references Student(StudentID),
    AssessmentID int references Assessment(AssessmentID),
    Grade float
)

CREATE TABLE Enrollment (
    EnrollmentID INT IDENTITY(1,1) PRIMARY KEY,
    StudentID INT REFERENCES Student(StudentID),
    subjectID INT REFERENCES Subject(SubjectID),
    EnrollmentSemester NVARCHAR(50) NOT NULL, --"Spring", "Summer" or "Fall"
    EnrollmentYear INT NOT NULL
)
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

create procedure insertAssessment
(
    @SubjectCode nvarchar(10),
    @count int,
    @category nvarchar(50),
    @weight float,
    @criteria float
)
as
begin
    declare @subjectID int
    select @subjectID = SubjectID from Subject where SubjectCode = @SubjectCode
    declare @percent float;
    select @percent = @weight / @count

    while @count > 0
    begin
        insert into Assessment (SubjectID, AssessmentCategory, AssessmentWeight, AssessmentCriteria) values (@subjectID, @category, @percent, @criteria)
        set @count = @count - 1
    end
end
go

create procedure insertRandomStudentAssessment
as
begin
    declare @studentID int, @assessmentID int, @grade float

    set @studentID = 1
    set @assessmentID = 1

    while @studentID <= 20
    begin
        while @assessmentID <= 140
        begin
            set @grade = cast(rand() * 10 as decimal(3,1))
            insert into StudentAssessment values (@studentID, @assessmentID, @grade)
            set @assessmentID = @assessmentID + 1
        end
        set @studentID = @studentID + 1
        set @assessmentID = 1
    end
end
go

create procedure getGrade
    @studentCode varchar(15),
    @subjectCode varchar(10)
as
begin
    create table #temp (
        AssessmentCategory varchar(50),
        AssessmentWeight varchar(10),
        Grade float
    )

    insert into #temp
    select 
        AssessmentCategory,
        CAST(AssessmentWeight * 100 AS varchar(10)) + '%' as AssessmentWeight,
        Grade
    from StudentAssessment
    join Assessment on StudentAssessment.AssessmentId = Assessment.assessmentID
    join Student on StudentAssessment.StudentId = Student.StudentId
    join Subject on Assessment.SubjectId = Subject.SubjectId
    where Student.StudentId = (select StudentId from Student where StudentCode = @studentCode) and subjectCode = @subjectCode

    insert into #temp
    select
        AssessmentCategory + ' Total' as AssessmentCategory,
        CAST(sum(AssessmentWeight) * 100 AS varchar(10)) + '%' as AssessmentWeight,
        sum(Grade)/count(AssessmentCategory) as Grade
    from StudentAssessment
    join Assessment on StudentAssessment.AssessmentId = Assessment.assessmentID
    join Student on StudentAssessment.StudentId = Student.StudentId
    join Subject on Assessment.SubjectId = Subject.SubjectId
    where Student.StudentId = (select StudentId from Student where StudentCode = @studentCode) and subjectCode = @subjectCode
    group by AssessmentCategory

    select * from #temp
    order by AssessmentCategory asc, AssessmentWeight asc
end
go

CREATE PROCEDURE EnrollStudent
    @studentCode NVARCHAR(15),
    @subjectCode NVARCHAR(10),
    @semester NVARCHAR(50),
    @year INT
AS
BEGIN
    DECLARE @studentID INT, @subjectID INT, @prerequisiteID INT, @average FLOAT, @status NVARCHAR(15)

    SELECT @studentID = StudentID FROM Student WHERE StudentCode = @studentCode
    SELECT @subjectID = SubjectID, @prerequisiteID = SubjectPrerequisite FROM Subject WHERE SubjectCode = @subjectCode

    IF @prerequisiteID IS NOT NULL
    BEGIN
        DECLARE @prerequisiteSubjectCode NVARCHAR(10)
        SELECT @prerequisiteSubjectCode = SubjectCode FROM Subject WHERE SubjectID = @prerequisiteID
        EXEC getResult @studentCode, @prerequisiteSubjectCode, @average OUTPUT, @status OUTPUT

        IF @status = 'Not Passed'
        BEGIN
            RAISERROR('Cannot enroll. Prerequisite subject not passed.', 16, 1)
            RETURN
        END
    END

    INSERT INTO Enrollment (StudentID, SubjectID, EnrollmentSemester, EnrollmentYear)
    VALUES (@studentID, @subjectID, @semester, @year)
END
GO

CREATE PROCEDURE getAcademicRecord
    @studentCode NVARCHAR(15)
AS
BEGIN
    create table #AcademicRecord
    (
        SubjectCode varchar(10),
        SubjectName varchar(50),
        Status varchar(50)
    )

    DECLARE @SubjectCode nvarchar(10), @SubjectName nvarchar(50)
    Declare @average float
    DECLARE @status nvarchar(15)

    DECLARE subject_cursor CURSOR FOR 
    SELECT SubjectCode, SubjectName FROM Subject

    OPEN subject_cursor
    FETCH NEXT FROM subject_cursor INTO @SubjectCode, @SubjectName

    WHILE @@FETCH_STATUS = 0
    BEGIN
        exec getResult @studentCode, @SubjectCode, @average output, @status output
        insert into #AcademicRecord (SubjectCode, SubjectName, Status) values (@SubjectCode, @SubjectName, @status)

        FETCH NEXT FROM subject_cursor INTO @SubjectCode, @SubjectName
    END

    select * from #AcademicRecord

    -- Close and deallocate the cursor
    CLOSE subject_cursor
    DEALLOCATE subject_cursor
END
GO

create procedure getResult
(
    @studentCode nvarchar(15),
    @subjectCode nvarchar(10),
    @average float output,
    @status nvarchar(15) output
)
as
begin
    declare @subjectID int
    declare @passed int
    select @subjectID = SubjectID from Subject where SubjectCode = @subjectCode

    select @average = sum(Grade * AssessmentWeight), @passed = sum(Passed)
    from (
        select 
            assessmentCategory,
            sum(AssessmentWeight) as AssessmentWeight,
            SUM(Grade)/count(assessmentCategory) as Grade, 
            AssessmentCriteria, 
            case 
                when AssessmentCriteria > 0 and SUM(Grade)/count(assessmentCategory) < AssessmentCriteria then 1 
                when AssessmentCriteria = 0 and SUM(Grade)/count(assessmentCategory) = AssessmentCriteria then 1 
                else 0 
            end as Passed
        from StudentAssessment SA
        join assessment A on SA.AssessmentID = A.AssessmentID
        join Student S on SA.StudentID = S.StudentID
        where StudentCode = @studentCode and SubjectID = @subjectID
        group by assessmentCategory, AssessmentCriteria
    ) as temp

    if @passed = 0
        set @status = 'Passed'
    else set @status = 'Not Passed'

    if @average < 5
        set @status = 'Not Passed'
end
go

create procedure getReport
    @studentCode varchar(15),
    @subjectCode varchar(10)
as
begin
    create table #temp (
        AssessmentCategory varchar(50),
        AssessmentWeight varchar(10),
        Grade float
    )

    insert into #temp
    exec getGrade @studentCode, @subjectCode

    declare @average float
    declare @status nvarchar(15)
    exec getResult @studentCode, @subjectCode, @average output, @status output

    insert into #temp
    values ('Course Average', @status, @average)

    select * from #temp
end
go



---------- Insert into subject table ----------

insert into Subject values ('CSI104', 'Introduction to Computer Science', 3, NULL);
insert into Subject values ('PRF192', 'Programming Fundamentals', 3, NULL);
insert into Subject values ('MAE101', 'Mathematics for Engineering', 3, NULL);
insert into Subject values ('CEA201', 'Computer Organization and Architecture', 3, NULL);
insert into Subject values ('SSL101c', 'Academic Skills for University Success', 3, NULL);

-- Second semester

insert into Subject values ('MAD101', 'Discrete mathematics', 3, NULL);
insert into Subject values ('OSG202', 'Operating System', 3, NULL);
insert into Subject values ('NWC203c', 'Computer Networking', 3, NULL);
insert into Subject values ('SSG104', 'Communication and In-Group Working Skills', 3, NULL);
insert into Subject values ('PRO192', 'Object-Oriented Programming', 3, 2);

-- Third semester

insert into Subject values ('JPD113', 'Elementary Japanese 1- A1.1', 3, NULL);
insert into Subject values ('CSD201', 'Data Structures and Algorithm', 3, 10);
insert into Subject values ('DBI202', 'Database Systems', 3, NULL);
insert into Subject values ('WED201c', 'Web Design', 3, NULL);

-- Fourth semester

insert into Subject values ('MAS291', 'Statistics & Probability', 3, 3);
insert into Subject values ('JPD123', 'Elementary Japanese 1- A1.2', 3, 11);
insert into Subject values ('IOT102', 'Internet of Things', 3, NULL);
insert into Subject values ('PRJ301', 'Java Web Application Development', 3, 13);
insert into Subject values ('PRJ311', 'Java Web Application Development Lab', 3, 2);
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
go

---------- Insert into Assessment table ----------

exec insertAssessment 'CSI104', 2, 'Group presentation', 0.1 , 0
exec insertAssessment 'CSI104', 2, 'Lab', 0.2 , 0
exec insertAssessment 'CSI104', 3, 'Progress test', 0.3 , 0
exec insertAssessment 'CSI104', 1, 'Final exam', 0.4, 4

exec insertAssessment 'PRF192', 2, 'Progress test', 0.1, 0
exec insertAssessment 'PRF192', 1, 'Assignment', 0.1, 0
exec insertAssessment 'PRF192', 5, 'Workshop', 0.1, 0
exec insertAssessment 'PRF192', 1, 'Practical Exam', 0.4, 0
exec insertAssessment 'PRF192', 1, 'Final exam', 0.3, 4

exec insertAssessment 'MAE101', 3, 'Assignment/Exercises', 0.3, 0
exec insertAssessment 'MAE101', 3, 'Progress Test', 0.3, 0
exec insertAssessment 'MAE101', 1, 'Final Exam', 0.4, 4

exec insertAssessment 'CEA201', 2, 'Assignment', 0.3, 0
exec insertAssessment 'CEA201', 4, 'Exercises', 0.3, 0
exec insertAssessment 'CEA201', 1, 'Final exam', 0.4, 4

exec insertAssessment 'SSL101c', 1, 'Theoretical Exam', 1, 4

exec insertAssessment 'MAD101', 3, 'Progress Test', 0.3, 0
exec insertAssessment 'MAD101', 3, 'Assignments/Exercises', 0.3, 0
exec insertAssessment 'MAD101', 1, 'Final Exam', 0.4, 4

exec insertAssessment 'OSG202', 4, 'Lab', 0.2, 0
exec insertAssessment 'OSG202', 1, 'Presentation', 0.2, 0
exec insertAssessment 'OSG202', 2, 'Progress test', 0.2, 0
exec insertAssessment 'OSG202', 1, 'Final exam', 0.4, 4

exec insertAssessment 'NWC203c', 1, 'Practical Exam', 0.5, 4
exec insertAssessment 'NWC203c', 1, 'Theoretical Exam', 0.5, 4

exec insertAssessment 'SSG104', 3, 'Activity', 0.15, 0
exec insertAssessment 'SSG104', 2, 'Group Assignment', 0.2, 0
exec insertAssessment 'SSG104', 3, 'Group Project', 0.3, 0
exec insertAssessment 'SSG104', 1, 'Participation', 0.1, 0
exec insertAssessment 'SSG104', 1, 'Quiz', 0.05, 0
exec insertAssessment 'SSG104', 1, 'Final exam', 0.2, 4

exec insertAssessment 'PRO192', 1, 'Assignment', 0.2, 0
exec insertAssessment 'PRO192', 6, 'Lab', 0.1, 0
exec insertAssessment 'PRO192', 1, 'Practical Exam', 0.3, 0
exec insertAssessment 'PRO192', 2, 'Progress Test', 0.1, 0
exec insertAssessment 'PRO192', 1, 'Final exam', 0.3, 4

exec insertAssessment 'JPD113', 2, 'Small Test', 0.2, 0
exec insertAssessment 'JPD113', 1, 'Participation', 0.1, 0
exec insertAssessment 'JPD113', 1, 'Mid-term', 0.3, 0
exec insertAssessment 'JPD113', 1, 'Practical exam', 0.3, 4
exec insertAssessment 'JPD113', 1, 'Practical exam', 0.1, 4

exec insertAssessment 'CSD201', 2, 'Progress test', 0.2, 0
exec insertAssessment 'CSD201', 2, 'Assignment', 0.2, 0
exec insertAssessment 'CSD201', 1, 'Practical Exam', 0.3, 0
exec insertAssessment 'CSD201', 1, 'Final exam', 0.3, 4

exec insertAssessment 'DBI202', 1, 'Assignment', 0.2, 0
exec insertAssessment 'DBI202', 5, 'Lab', 0.1, 0
exec insertAssessment 'DBI202', 1, 'Practical Exam', 0.3, 0
exec insertAssessment 'DBI202', 2, 'Progress test', 0.1, 0
exec insertAssessment 'DBI202', 1, 'Final exam', 0.3, 4

exec insertAssessment 'WED201c', 1, 'Practical Exam', 0.5, 4
exec insertAssessment 'WED201c', 1, 'Theoretical Exam', 0.5, 4

exec insertAssessment 'MAS291', 2, 'Assignment', 0.2, 0
exec insertAssessment 'MAS291', 1, 'Computer Project', 0.15, 0
exec insertAssessment 'MAS291', 3, 'Progress Test', 0.3, 0
exec insertAssessment 'MAS291', 1, 'Final exam', 0.35, 4

exec insertAssessment 'JPD123', 2, 'Small Test', 0.2, 0
exec insertAssessment 'JPD123', 1, 'Participation', 0.1, 0
exec insertAssessment 'JPD123', 1, 'Mid-term', 0.3, 0
exec insertAssessment 'JPD123', 1, 'Practical exam', 0.3, 4
exec insertAssessment 'JPD123', 1, 'Practical exam', 0.1, 4

exec insertAssessment 'IOT102', 1, 'Active learning', 0.1, 0
exec insertAssessment 'IOT102', 1, 'Presentation', 0.1, 0
exec insertAssessment 'IOT102', 2, 'Progress test', 0.1, 0
exec insertAssessment 'IOT102', 1, 'Project', 0.3, 0
exec insertAssessment 'IOT102', 1, 'Final exam', 0.4, 4

exec insertAssessment 'PRJ301', 6, 'On-going Assessment', 0.6, 0.5
exec insertAssessment 'PRJ301', 1, 'Final Project Presentation', 0.4, 4

exec insertAssessment 'PRJ311', 15, 'Lab', 0.4, 0
exec insertAssessment 'PRJ311', 1, 'Midterm test', 0.2, 0
exec insertAssessment 'PRJ311', 1, 'Participation in Discussions', 0.1, 0
exec insertAssessment 'PRJ311', 1, 'Final exam', 0.3, 4
go

---------- Insert into StudentAssessment table ----------

exec insertRandomStudentAssessment
go