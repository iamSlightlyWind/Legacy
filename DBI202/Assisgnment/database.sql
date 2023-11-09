select * from Subject
select * from Student
select * from Assessment
select * from StudentAssessment
select * from Enrollment

select * from sys.tables
select * from sys.triggers
select * from sys.procedures

select s1.subjectCode, S2.subjectCode as prerequisiteCode
from subject S1
left join subject S2 on S1.SubjectPrerequisite = s2.subjectID