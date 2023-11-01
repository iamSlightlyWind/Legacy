use DBI_ASM
go

/*
-- join subject with subject on prerequisite, then select both names
select s1.code as subject, s2.code as prerequisite
from Subject s1
join Subject s2 on s1.prerequisite = s2.id
go
*/

-- get first character of each words in last name, concat with first name
select id, firstName, lastName, concat(firstName, substring(lastName, 1, 1), substring(lastName, charindex(' ', lastName) + 1, 1), id) as studentCode
from Student