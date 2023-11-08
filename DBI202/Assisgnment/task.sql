use DBI_ASM
go

-- insert random grades for all subjects for all students
exec insertRandomStudentAssessment
go

-- get grades for a student
exec getReport 'HuongLT0003', 'MAD101'

-- get academic record for a student
exec getAcademicRecord 'QuocNA0010'

-- enroll a student into a course
exec enrollStudent 'HaiVD0005', 'MAE101', 'Spring', 2021
exec enrollStudent 'HaiVD0005', 'MAS291', 'Fall', 2021

-- insert assessments for a subject
exec insertAssessment 'PRJ311', 15, 'Lab', 0.4, 0
exec insertAssessment 'PRJ311', 1, 'Midterm test', 0.2, 0
exec insertAssessment 'PRJ311', 1, 'Participation in Discussions', 0.1, 0
exec insertAssessment 'PRJ311', 1, 'Final exam', 0.3, 4