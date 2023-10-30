use DBI_ASM
go

create table Subject
(
    id int primary key,
    code nvarchar(10) not null,
    name nvarchar(50) not null,
    credit int not null,
    prerequisite int references subject(id)
)
go

create table Student
(
    id int identity(1,1) primary key,
    firstName nvarchar(50) not null,
    lastName nvarchar(50) not null,
)
go

create table StudentCode
(
    studentID int references student(id),
    studentCode nvarchar(15) not null
)

create table Assessment
(
    id int identity(1,1) primary key,
    type nvarchar(50) not null,
    name nvarchar(50) not null,
    criteria nvarchar(50),
    weight int not null,
    time int
)
go

create table Grade
(
    subject int references subject(id),
    student int references student(id),
    assessment int references assessment(id),
    mark int not null,
    primary key (subject, student, assessment)
)
go

use DBI_ASM
go

---------- Insert into subject table ----------

-- First semester

insert into Subject values (9982, 'CSI104', 'Introduction to Computer Science', 3, NULL);
insert into Subject values (9569, 'PRF192', 'Programming Fundamentals', 3, NULL);
insert into Subject values (10326, 'MAE101', 'Mathematics for Engineering', 3, NULL);
insert into Subject values (10014, 'CEA201', 'Computer Organization and Architecture', 3, NULL);
insert into Subject values (10085, 'SSL101c', 'Academic Skills for University Success', 3, NULL);

-- Second semester

insert into Subject values (9241, 'MAD101', 'Discrete mathematics', 3, NULL);
insert into Subject values (10114, 'OSG202', 'Operating System', 3, NULL);
insert into Subject values (10173, 'NWC203c', 'Computer Networking', 3, NULL);
insert into Subject values (10084, 'SSG104', 'Communication and In-Group Working Skills', 3, NULL);
insert into Subject values (10296, 'PRO192', 'Object-Oriented Programming', 3, 9569);

-- Third semester

insert into Subject values (9226, 'JPD113', 'Elementary Japanese 1- A1.1', 3, NULL);
insert into Subject values (10015, 'CSD201', 'Data Structures and Algorithm', 3, 10296);
insert into Subject values (10010, 'DBI202', 'Database Systems', 3, NULL);
insert into Subject values (10021, 'LAB211', 'OOP with Java Lab', 3, 10296);
insert into Subject values (9966, 'WED201c', 'Web Design', 3, NULL);

-- Fourth semester

insert into Subject values (9243, 'MAS291', 'Statistics & Probability', 3, 10326);
insert into Subject values (9228, 'JPD123', 'Elementary Japanese 1- A1.2', 3, 9226);
insert into Subject values (10031, 'IOT102', 'Internet of Things', 3, NULL);
insert into Subject values (10032, 'PRJ301', 'Java Web Application Development', 3, 10010);
insert into Subject values (10033, 'PRJ311', 'Java Web Application Development Lab', 3, 9569);

---------- Insert into student table ----------

insert into Student values ('A', 'Nguyen Van');
insert into Student values ('B', 'Tran Van');
insert into Student values ('C', 'Le Van');
insert into Student values ('D', 'Pham Van');
insert into Student values ('E', 'Hoang Van');
insert into Student values ('F', 'Vu Van');
insert into Student values ('G', 'Dang Van');
insert into Student values ('H', 'Bui Van');
insert into Student values ('I', 'Do Van');
insert into Student values ('J', 'Ho Van');
insert into Student values ('K', 'Ngo Van');
insert into Student values ('L', 'Duong Van');
insert into Student values ('M', 'Ly Van');
insert into Student values ('N', 'An Van');
insert into Student values ('O', 'Phan Van');
insert into Student values ('P', 'Dinh Van');
insert into Student values ('Q', 'Hua Van');
insert into Student values ('R', 'Nghiem Van');
insert into Student values ('S', 'Bach Van');
insert into Student values ('T', 'Phung Van');
insert into Student values ('U', 'Pho Van');
insert into Student values ('V', 'Chu Van');
insert into Student values ('W', 'Kieu Van');
insert into Student values ('X', 'Trieu Van');
insert into Student values ('Y', 'Truong Van');
insert into Student values ('Z', 'Tong Van');