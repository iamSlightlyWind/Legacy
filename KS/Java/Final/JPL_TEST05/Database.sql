use master
go

IF EXISTS (SELECT 1
FROM sys.databases
WHERE name = 'JPL_TEST05')
BEGIN
    ALTER DATABASE JPL_TEST05 SET SINGLE_USER WITH ROLLBACK IMMEDIATE
    DROP DATABASE JPL_TEST05
END
go

create database JPL_TEST05
go

use JPL_TEST05
go

create table person
(
    personid int identity not null,
    person_name varchar(50) not null,
    bod date null,

    primary key (personid)
)

create table video
(
    video_id int IDENTITY not null,
    person_id_upload int not null,
    title varchar(50) not null,
    duration int not null CHECK (duration > 0),
    url varchar(500) not null,
    upload_date datetime not null,
    is_published bit not null CHECK (is_published IN (0, 1)),

    primary key (video_id),
    foreign key (person_id_upload) references person(personid)
)

create table person_video
(
    person_id int not null,
    video_id int not null,
    rate int null CHECK (rate BETWEEN 1 AND 5),
    is_liked bit null CHECK (is_liked IN (0, 1)),
    view_total int null,

    primary key (person_id, video_id),
    foreign key (person_id) references person(personid),
    foreign key (video_id) references video(video_id)
)
go

insert into person (person_name, bod) values ('Alice', '1990-01-01');
insert into person (person_name, bod) values ('Bob', '1985-05-15');
insert into person (person_name, bod) values ('Charlie', '1992-08-22');

insert into video (person_id_upload, title, duration, url, upload_date, is_published) values (1, 'Video 1', 120, 'http://a.com/video1', '2023-01-01 10:00:00', 1);
insert into video (person_id_upload, title, duration, url, upload_date, is_published) values (2, 'Video 2', 90, 'http://a.com/video2', '2023-02-01 11:00:00', 1);
insert into video (person_id_upload, title, duration, url, upload_date, is_published) values (3, 'Video 3', 150, 'http://a.com/video3', '2023-03-01 12:00:00', 0);
insert into video (person_id_upload, title, duration, url, upload_date, is_published) values (1, 'Video 4', 200, 'http://a.com/video4', '2023-04-01 13:00:00', 1);
insert into video (person_id_upload, title, duration, url, upload_date, is_published) values (2, 'Video 5', 180, 'http://a.com/video5', '2023-05-01 14:00:00', 0);

insert into person_video (person_id, video_id, rate, is_liked, view_total) values (1, 1, 5, 1, 100);
insert into person_video (person_id, video_id, rate, is_liked, view_total) values (2, 2, 4, 1, 150);
insert into person_video (person_id, video_id, rate, is_liked, view_total) values (3, 3, 3, 0, 200);
insert into person_video (person_id, video_id, rate, is_liked, view_total) values (1, 4, 5, 1, 250);
insert into person_video (person_id, video_id, rate, is_liked, view_total) values (2, 5, 4, 0, 300);
go

create or alter procedure personExist
    @person_id int,
    @result bit output
as
begin
    if exists (select 1 from person where personid = @person_id)
        set @result = 1
    else
        set @result = 0
end
go

create or alter procedure getVideosMostView
@limit int
as
begin
    select top (@limit) video.video_id, video.person_id_upload, video.title, video.duration, video.url, video.upload_date, video.is_published, person_video.view_total
    from video
    join person_video on video.video_id = person_video.video_id
    order by person_video.view_total desc
end
go

exec getVideosMostView 2