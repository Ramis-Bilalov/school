// Шаг 2
create table license
(
    id     int                 not null auto_increment,
    name   varchar(255) unique not null,
    status varchar(255) unique not null;
);
create table person
(
    id   int not null auto_increment,
    name varchar(255),
    age  int not null,
    foreign key (license_id) references license (id),
    foreign key (car_id) references cars (id)
);
create table cars
(
    id    int                 not null auto_increment,
    brand varchar(255) unique not null,
    model varchar(255)        not null,
    price int                 not null
);