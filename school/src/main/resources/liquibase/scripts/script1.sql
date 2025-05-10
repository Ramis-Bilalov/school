-- liquibase formatted sql

-- changeset rbilalov:1

create table if not exists student
(
    id   serial,
    age  integer,
    name varchar(255) unique not null,
    foreign key (faculty_id) references faculty (id)
);

-- changeset rbilalov:2

create index students_name_index on student(name);

-- changeset rbilalov:3

create index faculties_name_index on faculty(name);

-- changeset rbilalov:4

create index faculties_color_index on faculty(color);