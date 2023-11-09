select * from Subject
select * from Student
select * from Assessment
select * from StudentAssessment
select * from Enrollment

-- get grades for a student
exec getReport 'MinhTT0002', 'MAE101'

-- get academic record for a student
exec getAcademicRecord 'TuanNV0001'

-- enroll a student into a course
exec enrollStudent 'QuocNA0010', 'MAE101', 'Spring', 2021
exec enrollStudent 'QuocNA0010', 'MAS291', 'Fall', 2021 -- fail since not passed

-- view all subject and its prerequisites
select s1.subjectCode, S2.subjectCode as prerequisiteCode
from subject S1
left join subject S2 on S1.SubjectPrerequisite = s2.subjectID

select * from subject
-- view all assessments of a subject
select
    Assessment.assessmentCategory,
    AssessmentDetails.type,
    count(Assessment.assessmentCategory) as Part,
    sum(AssessmentWeight) as AssessmentWeight,
    Assessment.AssessmentCriteria,
    AssessmentDetails.Duration,
    AssessmentDetails.QuestionType,
    AssessmentDetails.NoQuestion,
    AssessmentDetails.Knowledge,
    AssessmentDetails.GradingGuide,
    AssessmentDetails.Note
from assessment
join subject on assessment.subjectID = subject.subjectID
join AssessmentDetails on subject.subjectID = AssessmentDetails.subjectID and assessment.assessmentCategory = AssessmentDetails.assessmentCategory
where subjectCode = 'IOT102'
group by
    Assessment.assessmentCategory,
    AssessmentDetails.type,
    Assessment.AssessmentCriteria,
    AssessmentDetails.Duration,
    AssessmentDetails.QuestionType,
    AssessmentDetails.NoQuestion,
    AssessmentDetails.Knowledge,
    AssessmentDetails.GradingGuide,
    AssessmentDetails.Note

-- A query that uses ORDER BY 
-- to sort the result set in descending order by the Grade column
select subjectCode, studentCode, assessmentCategory, Grade
from StudentAssessment
join Student on StudentAssessment.studentID = Student.studentID
join Assessment on StudentAssessment.assessmentID = Assessment.assessmentID
join Subject on Assessment.subjectID = Subject.subjectID
where SubjectCode = 'PRF192' and AssessmentCategory = 'Final exam'
order by Grade desc

-- A query that uses INNER JOINS
SELECT 
    S.studentCode,
    sj.subjectCode
FROM 
    Student S
INNER JOIN 
    Enrollment E ON S.StudentID = E.StudentID
INNER JOIN 
    Subject Sj ON E.SubjectID = Sj.SubjectID;

-- A query that uses a sub-query in the WHERE clause
SELECT 
    S.FirstName, 
    S.LastName
FROM 
    Student S
WHERE 
    S.StudentID IN (
        SELECT 
            E.StudentID
        FROM 
            Enrollment E
        WHERE 
            E.SubjectID IN (
                SELECT 
                    Sub.SubjectID
                FROM 
                    Subject Sub
                WHERE 
                    Sub.SubjectPrerequisite IS NOT NULL
            )
    )

--  A query that uses a sub-query as a relation
SELECT 
    S.FirstName, 
    S.LastName
FROM 
    Student S
WHERE 
    S.StudentID IN (
        SELECT 
            E.StudentID
        FROM 
            Enrollment E
        WHERE 
            E.SubjectID = 1
    )

-- A query that uses aggregate functions
select subjectCode, sum(Grade * AssessmentWeight) as TotalGrade
from StudentAssessment SA
join Assessment A on SA.assessmentID = A.assessmentID
join Subject S on A.subjectID = S.subjectID
where subjectCode = 'PRF192' and studentID = 3
group by subjectCode

-- A query that uses the GROUP BY and HAVING clauses
select subjectCode, sum(Grade * AssessmentWeight) as TotalGrade
from StudentAssessment SA
join Assessment A on SA.assessmentID = A.assessmentID
join Subject S on A.subjectID = S.subjectID
where studentID = 3
group by subjectCode
having sum(Grade * AssessmentWeight) > 5

--------------------------- Triggers And Procedures
-- get average grade of all subjects for all students 
drop view StudentPerformance

CREATE VIEW StudentPerformance AS
SELECT 
    S.FirstName, 
    S.LastName, 
    Sub.SubjectName, 
    SUM(SA.Grade * A.AssessmentWeight) AS AverageGrade
FROM 
    Student S
JOIN 
    Enrollment E ON S.StudentID = E.StudentID
JOIN 
    Subject Sub ON E.SubjectID = Sub.SubjectID
JOIN 
    StudentAssessment SA ON S.StudentID = SA.StudentID
JOIN 
    Assessment A ON SA.AssessmentID = A.AssessmentID AND A.SubjectID = Sub.SubjectID
GROUP BY 
    S.FirstName, 
    S.LastName, 
    Sub.SubjectName;

select * from StudentPerformance
exec getReport 'DucPH0004', 'SSG104'


-- When adding a student, get part of name + id to get studentCode
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

-- An assessment can have many parts. This will get the number of assessments as an argument
-- and insert amount of assessment
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

-- Get grades of a subject for a student (getGrade), add records as total of each assessment category
-- then add final record of whether or not said student passed said subject
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


-- get studentAssessment of a subject for a student
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


-- use getGrade, alongside AssessmentCriteria to determine if each assessment category and
-- totalaverage meets the passing conditions
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

-- getResult of all subject
CREATE PROCEDURE getAcademicRecord
    @studentCode NVARCHAR(15)
AS
BEGIN
    CREATE TABLE #AcademicRecord
    (
        SubjectCode NVARCHAR(10),
        SubjectName NVARCHAR(50),
        Semester NVARCHAR(50),
        Status NVARCHAR(50)
    )

    DECLARE @studentID INT, @SubjectCode NVARCHAR(10), @SubjectName NVARCHAR(50), @semester NVARCHAR(50)
    DECLARE @average FLOAT, @status NVARCHAR(15)

    SELECT @studentID = StudentID FROM Student WHERE StudentCode = @studentCode

    DECLARE subject_cursor CURSOR FOR 
    SELECT Subject.SubjectCode, Subject.SubjectName, CONCAT(Enrollment.EnrollmentSemester, Enrollment.EnrollmentYear)
    FROM Subject
    LEFT JOIN Enrollment ON Subject.SubjectID = Enrollment.SubjectID AND Enrollment.StudentID = @studentID

    OPEN subject_cursor
    FETCH NEXT FROM subject_cursor INTO @SubjectCode, @SubjectName, @semester

    WHILE @@FETCH_STATUS = 0
    BEGIN
        EXEC getResult @studentCode, @SubjectCode, @average OUTPUT, @status OUTPUT
        INSERT INTO #AcademicRecord (SubjectCode, SubjectName, Semester, Status) VALUES (@SubjectCode, @SubjectName, @semester, @status)

        FETCH NEXT FROM subject_cursor INTO @SubjectCode, @SubjectName, @semester
    END

    SELECT * FROM #AcademicRecord

    CLOSE subject_cursor
    DEALLOCATE subject_cursor
END
GO

-- Enroll a student to a subject. Check if that subject has prerequiste subject and check that too.
-- Fails to enroll a student if not passed prerequiste subject
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

---------------- Create table
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
create table AssessmentDetails
(
    subjectID int references Subject(SubjectID),
    type nvarchar(50),
    AssessmentCategory nvarchar(50),
    Duration int,
    QuestionType nvarchar(255),
    NoQuestion int,
    Knowledge nvarchar(255),
    GradingGuide nvarchar(255),
    Note nvarchar(max)
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