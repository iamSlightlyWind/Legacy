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
go

CREATE INDEX idx_AssessmentID
ON Assessment (AssessmentID);

CREATE INDEX idx_StudentAssessmentID
ON  StudentAssessment (AssessmentID);

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

CREATE PROCEDURE EnrollAndGenerateGrades
    @studentCode NVARCHAR(15),
    @subjectCode NVARCHAR(10),
    @semester NVARCHAR(50),
    @year INT
AS
BEGIN
    DECLARE @studentID INT, @subjectID INT, @assessmentID INT, @grade FLOAT

    EXEC EnrollStudent @studentCode, @subjectCode, @semester, @year
    IF @@ERROR = 0
    BEGIN
        SELECT @studentID = StudentID FROM Student WHERE StudentCode = @studentCode
        SELECT @subjectID = SubjectID FROM Subject WHERE SubjectCode = @subjectCode

        SET @assessmentID = 1

        WHILE @assessmentID <= 103
        BEGIN
            IF EXISTS (SELECT 1 FROM Assessment WHERE AssessmentID = @assessmentID AND SubjectID = @subjectID)
            BEGIN
                SET @grade = CAST(RAND() * 10 AS DECIMAL(3,1))
                INSERT INTO StudentAssessment VALUES (@studentID, @assessmentID, @grade)
            END

            SET @assessmentID = @assessmentID + 1
        END
    END
END
GO

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

insert into Subject values ('CSD201', 'Data Structures and Algorithm', 3, 10);
insert into Subject values ('DBI202', 'Database Systems', 3, NULL);
insert into Subject values ('WED201c', 'Web Design', 3, NULL);

-- Fourth semester

insert into Subject values ('MAS291', 'Statistics & Probability', 3, 3);
insert into Subject values ('IOT102', 'Internet of Things', 3, NULL);
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

exec insertAssessment 'MAE101', 3, 'Assignments/Exercises', 0.3, 0
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

exec insertAssessment 'IOT102', 1, 'Active learning', 0.1, 0
exec insertAssessment 'IOT102', 1, 'Presentation', 0.1, 0
exec insertAssessment 'IOT102', 2, 'Progress test', 0.1, 0
exec insertAssessment 'IOT102', 1, 'Project', 0.3, 0
exec insertAssessment 'IOT102', 1, 'Final exam', 0.4, 4
go

---------- Insert into StudentAssessment table ----------

exec EnrollAndGenerateGrades 'TuanNV0001', 'CSI104', 'Spring', 2021
exec EnrollAndGenerateGrades 'TuanNV0001', 'PRF192', 'Summer', 2022
exec EnrollAndGenerateGrades 'TuanNV0001', 'MAE101', 'Fall', 2023
exec EnrollAndGenerateGrades 'TuanNV0001', 'CEA201', 'Spring', 2024
exec EnrollAndGenerateGrades 'TuanNV0001', 'SSL101c', 'Spring', 2025
exec EnrollAndGenerateGrades 'TuanNV0001', 'MAD101', 'Summer', 2026
exec EnrollAndGenerateGrades 'TuanNV0001', 'OSG202', 'Fall', 2027
exec EnrollAndGenerateGrades 'TuanNV0001', 'NWC203c', 'Spring', 2028
exec EnrollAndGenerateGrades 'TuanNV0001', 'SSG104', 'Summer', 2029
exec EnrollAndGenerateGrades 'TuanNV0001', 'PRO192', 'Fall', 2030

exec EnrollAndGenerateGrades 'MinhTT0002', 'CSI104', 'Spring', 2021
exec EnrollAndGenerateGrades 'MinhTT0002', 'PRF192', 'Summer', 2022
exec EnrollAndGenerateGrades 'MinhTT0002', 'MAE101', 'Fall', 2023
exec EnrollAndGenerateGrades 'MinhTT0002', 'CEA201', 'Spring', 2024
exec EnrollAndGenerateGrades 'MinhTT0002', 'SSL101c', 'Spring', 2025
exec EnrollAndGenerateGrades 'MinhTT0002', 'MAD101', 'Summer', 2026
exec EnrollAndGenerateGrades 'MinhTT0002', 'OSG202', 'Fall', 2027
exec EnrollAndGenerateGrades 'MinhTT0002', 'NWC203c', 'Spring', 2028
exec EnrollAndGenerateGrades 'MinhTT0002', 'SSG104', 'Summer', 2029
exec EnrollAndGenerateGrades 'MinhTT0002', 'PRO192', 'Fall', 2030

exec EnrollAndGenerateGrades 'HuongLT0003', 'CSI104', 'Spring', 2021
exec EnrollAndGenerateGrades 'HuongLT0003', 'PRF192', 'Summer', 2022
exec EnrollAndGenerateGrades 'HuongLT0003', 'MAE101', 'Fall', 2023
exec EnrollAndGenerateGrades 'HuongLT0003', 'CEA201', 'Spring', 2024
exec EnrollAndGenerateGrades 'HuongLT0003', 'SSL101c', 'Spring', 2025
exec EnrollAndGenerateGrades 'HuongLT0003', 'MAD101', 'Summer', 2026
exec EnrollAndGenerateGrades 'HuongLT0003', 'OSG202', 'Fall', 2027
exec EnrollAndGenerateGrades 'HuongLT0003', 'NWC203c', 'Spring', 2028
exec EnrollAndGenerateGrades 'HuongLT0003', 'SSG104', 'Summer', 2029
exec EnrollAndGenerateGrades 'HuongLT0003', 'PRO192', 'Fall', 2030

exec EnrollAndGenerateGrades 'DucPH0004', 'CSI104', 'Spring', 2021
exec EnrollAndGenerateGrades 'DucPH0004', 'PRF192', 'Summer', 2022
exec EnrollAndGenerateGrades 'DucPH0004', 'MAE101', 'Fall', 2023
exec EnrollAndGenerateGrades 'DucPH0004', 'CEA201', 'Spring', 2024
exec EnrollAndGenerateGrades 'DucPH0004', 'SSL101c', 'Spring', 2025
exec EnrollAndGenerateGrades 'DucPH0004', 'MAD101', 'Summer', 2026
exec EnrollAndGenerateGrades 'DucPH0004', 'OSG202', 'Fall', 2027
exec EnrollAndGenerateGrades 'DucPH0004', 'NWC203c', 'Spring', 2028
exec EnrollAndGenerateGrades 'DucPH0004', 'SSG104', 'Summer', 2029
exec EnrollAndGenerateGrades 'DucPH0004', 'PRO192', 'Fall', 2030

exec EnrollAndGenerateGrades 'HaiVD0005', 'CSI104', 'Spring', 2021
exec EnrollAndGenerateGrades 'HaiVD0005', 'PRF192', 'Summer', 2022
exec EnrollAndGenerateGrades 'HaiVD0005', 'MAE101', 'Fall', 2023
exec EnrollAndGenerateGrades 'HaiVD0005', 'CEA201', 'Spring', 2024
exec EnrollAndGenerateGrades 'HaiVD0005', 'SSL101c', 'Spring', 2025
exec EnrollAndGenerateGrades 'HaiVD0005', 'MAD101', 'Summer', 2026
exec EnrollAndGenerateGrades 'HaiVD0005', 'OSG202', 'Fall', 2027
exec EnrollAndGenerateGrades 'HaiVD0005', 'NWC203c', 'Spring', 2028
exec EnrollAndGenerateGrades 'HaiVD0005', 'SSG104', 'Summer', 2029
exec EnrollAndGenerateGrades 'HaiVD0005', 'PRO192', 'Fall', 2030
go

---------- Insert into AssessmentDetail table ----------

insert into AssessmentDetails values (1, 'on-going', 'Group presentation', 30, 'ppt and oral presentation', null, 'one of the appendices', 'each group selects one topic to present at slot 6', '')
insert into AssessmentDetails values (1, 'on-going', 'Lab', 60, 'practical exercises', null, 'technical aspect of studied topics', 'Labs are arranged relevant to the chapter theory', 'Student must finish labs and submit the results to the lecturer in class.')
insert into AssessmentDetails values (1, 'on-going', 'Progress test', 30, 'MC', null, 'Test 1: Chapters 1, 2, 3', 'Instruction and schedules for Progress Tests', 'Instructor has responsibility to review the test for students after graded.')
insert into AssessmentDetails values (1, 'final exam', 'Final exam', 60, 'Multiple choices', null, 'The exam questions must be updated', 'concepts, algorithms; all studied Chapters', '')

insert into AssessmentDetails values (2, 'quiz', 'Progress test', 20, 'essay or multiple choice', null, 'PT1: CLO1 - CLO4; PT2: CLO5 - CLO8', 'Progress test must be taken right after the last lectures of required material.', 'Instructor has responsibility to review the test for students after graded.')
insert into AssessmentDetails values (2, 'on-going', 'Assignment', null, 'a problem similar to real one', null, 'problem(s) to solve by programming using CLO2 - CLO8', 'Individual or team work, guided by instructor, submission by a given deadline', '')
insert into AssessmentDetails values (2, 'on-going', 'Workshop', null, 'practical exercises', null, 'W1: CLO1, CLO2, CLO3; W2, 3: LO4; W4: CLO6, CLO7; W5: CLO7, CLO8', 'Teachers support tutorials and students do them by themselves', 'A workshop will be evaluated at the beginning of the next lesson')
insert into AssessmentDetails values (2, 'practical exam', 'Practical Exam', 85, 'Preferable to be marked by scripts', null, 'problem(s) to solve by programming using CLO2 - CLO7', 'Automatic or manual evaluating', '')
insert into AssessmentDetails values (2, 'final exam', 'Final exam', 60, 'multiple choice', null, 'questions distribution:CLO1: 2 - 3; CLO2: 5 - 10; CLO3: 8 - 10; CLO4: 8 - 10; CLO5: 5 - 8; CLO6: 5 - 8; CLO7: 5 - 8; CLO8: 2 - 4. More than 70% new questions (for the current semester);', 'supervised by proctor(s) sent by exam board', '')

insert into AssessmentDetails values (3, 'on-going', 'Assignments/Exercises', null, 'At least 3/chapter', null, 'Chapters introduced', 'Students use notebooks for exercises', 'guided by instructor in class')
insert into AssessmentDetails values (3, 'on-going', 'Progress Test', 40, 'MC', null, 'Chapters introduced', 'by instructor', 'must be presented')
insert into AssessmentDetails values (3, 'final exam', 'Final Exam', 60, 'Computer gradable', null, 'All studied chapters', 'by Exam Board', '~10% of questions is a2 objective oriented')

insert into AssessmentDetails values (4, 'on-going', 'Assignment', null, 'Developing Assemly program', null, 'Basic programs', 'Teachers assess their works on their computers', '30% of total progress mark')
insert into AssessmentDetails values (4, 'on-going', 'Exercises', null, 'Writing', null, 'Studied chapters; knowledge and skills', 'Students write answers to their notebook', '30% of total progress mark')
insert into AssessmentDetails values (4, 'final exam', 'Final exam', 60, 'Multiple choice', null, 'All chapters; knowledge and skills of digital system', 'by Exam board', '40% of total progress mark')

insert into AssessmentDetails values (5, 'final exam', 'Theoretical Exam', 60, 'Multiple choice', null, 'The whole course content', 'by exam board', 'Final Exam')

insert into AssessmentDetails values (6, 'quiz', 'Progress Test', 30, 'MC', null, 'Chapters introduced', 'by instructor', 'must be presented')
insert into AssessmentDetails values (6, 'on-going', 'Assignments/Exercises', null, 'At least 5/ chapter', null, 'Chapters introduced', 'Students use notebooks for exercises', 'guided by instructor in class')
insert into AssessmentDetails values (6, 'final exam', 'Final Exam', 60, 'Computer gradable', null, 'All studied chapters. At least 6 questions/chapter', 'by Exam Board', '~10% of questions is a2 objective oriented')

insert into AssessmentDetails values (7, 'on-going', 'Lab', null, 'practical exercises', null, 'technical aspect of studied topics', 'guided by instructor', '')
insert into AssessmentDetails values (7, 'on-going', 'Presentation', null, 'a selelected topic', null, 'prepare at home, working in group, present in class hours', '', '')
insert into AssessmentDetails values (7, 'on-going', 'Progress test', 10, 'Multiple choices', null, 'Current chapter(s)', 'in class/remote, by instructor', 'must be presented')
insert into AssessmentDetails values (7, 'final exam', 'Final exam', 60, 'Multiple choices', null, 'concepts, algorithms; All chapters from 1 to 6; more than 70% new questions (for the current semester);', 'by exam board', '')

insert into AssessmentDetails values (8, 'final exam', 'Practical Exam', 120, null, null, 'All studied courses', 'by Exam Board', 'Customized from the exercises of this specialization.')
insert into AssessmentDetails values (8, 'final exam', 'Theoretical Exam', 60, 'Computer gradable', null, 'All studied courses. Each module of course contributes 2-3 questions.', 'by Exam Board', 'Customized from the quizzes of this specialization.')

insert into AssessmentDetails values (9, 'on-going', 'Activity', 40, 'OP1: Lecturers to choose a apporopriate course content to design activity for students to review the theory or apply theory in practice', null, 'ASM the acquainted knowledge which student obtained in the delivered chapters', 'in class, by instructor', 'Activity 1: LO1,2 Activity 2: LO3 5, 6, 7 Activity 3: LO4, 8')
insert into AssessmentDetails values (9, 'on-going', 'Group Assignment', 60, 'OP1: Guided by instructor in appropriate slots', null, 'ASM the acquainted knowledge which student obtained in the delivered chapters', 'guided by instructor', 'Group asm 1: LO8,9,10, 12 Group asm 2: LO 8,11,12')
insert into AssessmentDetails values (9, 'on-going', 'Group Project', null, 'OP1: See sheet: Group project', null, 'In or outside', '', '')
insert into AssessmentDetails values (9, 'on-going', 'Participation', null, 'OP1: based on schedule''s course', null, 'in class, by instructor', '', '')
insert into AssessmentDetails values (9, 'on-going', 'Quiz', 30, 'OP1: Multiple choice or Essay', null, 'Test the acquainted knowledge which student obtained in the delivered chapters', 'in class, by instructor. Should use EOS', '')
insert into AssessmentDetails values (9, 'final exam', 'Final exam', 30, 'Multiple choices', null, 'concepts, algorithms; all studied chapters', 'by exam board, using computer', 'LO1,2,3,6,7')

insert into AssessmentDetails values (10, 'on-going', 'Assignment', null, 'Option 1: 28 slots; Option 2 (For Constructivism Approach only): Follow lecturer''s proposal', null, 'All subjects in syllabus', 'in class, by teacher', '')
insert into AssessmentDetails values (10, 'on-going', 'Lab', 90, 'Option 1: 90''/each; Option 2 (For Constructivism Approach only): Follow lecturer''s proposal', null, '', 'in class, by teacher', '')
insert into AssessmentDetails values (10, 'on-going', 'Practical Exam', 90, null, null, '', 'By exam board', '')
insert into AssessmentDetails values (10, 'on-going', 'Progress Test', 30, 'Option 1: 30''/each; Option 2 (For Constructivism Approach only): Follow lecturer''s proposal', null, 'Option 1: 20/each; Option 2 (For Constructivism Approach only): Follow lecturer''s proposal', 'in class, by LMS system', 'Instruction and schedules for Progress Tests must be presented in the Course Implementation Plan approved by director of the campus. Progress test must be taken right after the last lectures of required material. Instructor has responsibility to review the test for students after graded.')
insert into AssessmentDetails values (10, 'final exam', 'Final Exam', 60, 'Multiple choices Marked by Computer', null, 'All subjects in syllabus', 'By Exam Board', 'The exam questions must be updated or different at least 70% to the previous ones.')

insert into AssessmentDetails values (11, 'quiz', 'Progress test', 20, 'Constructivism Approach', null, 'PT 1', 'LO1 - LO4', '3-8 for each LOx')
insert into AssessmentDetails values (11, 'on-going', 'Assignment', null, 'Constructivism Approach', null, 'AS1: LO1 - LO3 AS2: LO4-LO6', 'Individual or team work, guided by instructor, submission by a given deadline', '')
insert into AssessmentDetails values (11, 'practical exam', 'Practical Exam', 85, null, null, 'problem(s) to solve by programming (LO1, LO2, LO4, LO6)', 'supervised by proctor(s) sent by exam board', 'Automatic or manual evaluating')
insert into AssessmentDetails values (11, 'final exam', 'Final exam', 60, null, null, '50', 'supervised by proctor(s)', 'more than 70% new questions (for the current semester);')  

insert into AssessmentDetails values (12, 'on-going', 'Assignment', null, '28 slots', null, 'All subjects in syllabus', 'in class, by teacher', '')
insert into AssessmentDetails values (12, 'on-going', 'Lab', 90, null, null, '', 'in class, by teacher', '')
insert into AssessmentDetails values (12, 'on-going', 'Practical Exam', 90, null, null, '', 'By exam board', '')
insert into AssessmentDetails values (12, 'on-going', 'Progress test', 30, null, null, '20', 'in class, by LMS system', 'must be presented')
insert into AssessmentDetails values (12, 'final exam', 'Final exam', 60, null, null, '50', 'Exam room', 'The exam questions must be updated or different at least 70% to the previous ones.')

insert into AssessmentDetails values (13, 'final exam', 'Practical Exam', 120, null, null, 'The whole course content', 'by Exam Board', 'Customized from the Assignment')
insert into AssessmentDetails values (13, 'final exam', 'Theoretical Exam', 60, 'Multiple choice', 60, 'The whole course content. Each module of course contributes 3-5 questions.', 'By Exam Board.', 'Customized from the quizzes of this specialization')

insert into AssessmentDetails values (14, 'on-going', 'Assignment', null, 'at home', null, 'Chapters introduced', 'OP1: guided by the instructor in class, completed by student at home, submitted by deadline OP2: (For Constructivism Approach only): Follow lecturer''s proposal', '')
insert into AssessmentDetails values (14, 'on-going', 'Computer Project', null, 'at home', null, 'Chapters introduced', 'collect secondary data', '')
insert into AssessmentDetails values (14, 'on-going', 'Progress Test', 30, null, null, 'Chapters introduced', 'OP1: by instructor, and by suitable means (computer, paper, oral...) OP2: (For Constructivism Approach only): Follow lecturer''s proposal', 'OP1: Instruction and schedules for Progress tests must be presented in the Course Implementation Plan approved by director of the campus. OP2: (For Constructivism Approach only): Follow lecturer''s proposal Progress test must be taken right after the last lectures of required material. Instructor has resposibility to review the test for students after graded.')
insert into AssessmentDetails values (14, 'final exam', 'Final exam', 60, null, null, 'All studied chapters 3-10 questions/each. More than 70% new questions (for the current semester);', 'by Exam Board', '10% of questions is ABET a2-objective oriented')

insert into AssessmentDetails values (15, 'on-going', 'Active learning', null, 'From start to finish in schedule', null, 'Students ask good questions, hard work, self study online.', 'by instructor', '')
insert into AssessmentDetails values (15, 'on-going', 'Presentation', null, 'Option 1: Group. Option 2 (For Constructivism Approach only): Follow lecturer''s proposal', null, 'IoT idea/theory', 'by instructor', 'Scoring will be based on the guidelines in Tab "Presentation Guide"')
insert into AssessmentDetails values (15, 'on-going', 'Progress test', 30, 'Constructivism Approach', null, 'studied chapters knowledge and skills', 'by instructor', '')
insert into AssessmentDetails values (15, 'on-going', 'Project', null, 'Option 1: From start to finish in schedule. Option 2 (For Constructivism Approach only): Follow lecturer''s proposal', null, 'studied chapters knowledge and skills', 'by instructor', 'Scoring will be based on the guidelines in Tab "Project Guide"')
insert into AssessmentDetails values (15, 'final exam', 'Final exam', 60, null, null, 'All chapters; knowledge and skills', 'by Exam board', '')