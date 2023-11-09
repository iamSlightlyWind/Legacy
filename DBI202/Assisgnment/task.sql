select * from Subject
select * from Student
select * from Assessment
select * from StudentAssessment
select * from Enrollment

-- insert random grades for all subjects for all students
exec insertRandomStudentAssessment
go

-- get grades for a student
exec getReport 'MinhTT0002', 'MAE101'

-- get academic record for a student
exec getAcademicRecord 'TuanNV0001'

-- enroll a student into a course
exec enrollStudent 'QuocNA0010', 'MAE101', 'Spring', 2021
exec enrollStudent 'QuocNA0010', 'MAS291', 'Fall', 2021 -- fail since not passed

-- insert assessments for a subject

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