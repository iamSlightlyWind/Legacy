if exists (select *
from sys.databases
where name = 'DBI_ASM')
    drop database DBI_ASM

create database DBI_ASM
go