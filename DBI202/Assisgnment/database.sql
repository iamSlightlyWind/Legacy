select * from Student
select * from Assessment
select * from Subject
select * from Enrollment
select * from studentGrade

select * from sys.tables
select * from sys.triggers
select * from sys.procedures

-- get all courses with prerequisites
select S1.ID, S1.Code, S1.Name, S2.Code
from subject S1
left join Subject S2 on S1.Prerequisite = S2.ID

-- get assessment from a course
select S.Code, A.AssessmentID, A.Category, A.Criteria, count(A.Category) as Count
from Assessment a
join Subject S on A.subjectID = S.ID
group by S.Code, A.Category, A.Criteria, A.AssessmentID
having Code = 'PRF192'

-- get minimized assessment from a course
select S.Code, A.Category, A.Criteria, count(A.Category) as Count
from Assessment a
join Subject S on A.subjectID = S.ID
group by S.Code, A.Category, A.Criteria
having Code = 'PRF192'

select
    StudentCode,
    SubjectCode,
    AssessmentCategory,
    AssessmentCriteria,
    Grade,
    CASE 
        WHEN Grade >= AssessmentCriteria THEN 1 
        ELSE 0 
    END as Allowed
from StudentAssessment SA
join Student S on SA.StudentID = S.StudentID
join assessment A on SA.AssessmentID = A.AssessmentID
join subject ST on A.SubjectID = ST.SubjectID
where S.StudentID = 1

-- get grade for a subject
select *
from StudentAssessment SA
join assessment A on SA.AssessmentID = A.AssessmentID
join subject S on A.SubjectID = S.SubjectID
where S.SubjectID = 10296 and studentID = 5