// Шаг 3
select student.name,
       student.age
from student
         join faculty
              on student.faculty_id = faculty.id;

select student.name,
       student.age
from student
         inner join avatar
                    on avatar.student_id = student.id;
