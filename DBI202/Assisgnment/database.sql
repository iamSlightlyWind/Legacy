select * from Student
select * from Assessment
select * from Subject
select * from StudentAssessment

select * from sys.tables
select * from sys.triggers
select * from sys.procedures


drop table #AcademicRecord

create table #AcademicRecord
(
    SubjectCode varchar(10),
    AssessmentCategory varchar(50),
    AssessmentCriteria float,
    Grade float,
    AssessmentWeight float,
    Passed bit
)

declare @studentID int = 3
insert into #AcademicRecord
select
    S.SubjectCode,
    AssessmentCategory,
    AssessmentCriteria,
    SUM(Grade)/count(assessmentCategory) as Grade,
    sum(AssessmentWeight) as AssessmentWeight,
    case 
        when AssessmentCriteria > 0 and SUM(Grade)/count(assessmentCategory) < AssessmentCriteria then 1 
        when AssessmentCriteria = 0 and SUM(Grade)/count(assessmentCategory) = AssessmentCriteria then 1 
        else 0 
    end as Passed
from Subject S
join Assessment A on S.SubjectID = A.SubjectID
join StudentAssessment SA on A.AssessmentID = SA.AssessmentID
where SA.StudentID = @studentID
group by S.SubjectCode, AssessmentCategory, AssessmentCriteria

select
    SubjectCode,
    sum(Grade * AssessmentWeight) as Average
from #AcademicRecord
group by SubjectCode

exec getReport 'HuongLT0003', 'DBI202'

drop  procedure getAcademicRecord

go
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

exec getAcademicRecord 'DucPH0004'
exec getReport 'DucPH0004',  'PRF192'

select *
from Assessment