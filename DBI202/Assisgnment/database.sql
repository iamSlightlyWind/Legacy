select subjectID, round(sum(weight),2) as 'Total weight'
from assessment
group by subjectID

select subjectID, count(*) as 'Number of assessments'
from assessment
group by subjectID

select * from Subject
select * from Student
select * from StudentCode
select * from Assessment
select * from Enrollment
select * from studentGrade

select S.StudentID, StudentCode, FirstName, LastName
from Student S
join StudentCode SC on S.StudentID = SC.StudentID

-- print all tables names
select * from sys.tables
select * from sys.triggers
select * from sys.procedures