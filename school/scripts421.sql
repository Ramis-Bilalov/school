// Шаг 1
create table student
(
    id   int                 not null auto_increment,
    age  integer check ( age >= 16 ) default 20,
    name varchar(255) unique not null,
    foreign key (faculty_id) references faculty (id)
);

create table faculty
(
    id    int not null auto_increment,
    color varchar(255) unique,
    name  varchar(255) unique
);

