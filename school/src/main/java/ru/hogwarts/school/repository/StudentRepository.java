package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;
import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query(value = "SELECT COUNT(*) FROM student;", nativeQuery = true)
    int getAllStudentsCount();

    @Query(value = "SELECT AVG(age) FROM student;", nativeQuery = true)
    int getAverageStudentsAge();

    @Query(value = "SELECT * FROM student order by id desc limit 5", nativeQuery = true)
    Collection<Student> getFiveLastStudentsOrderById();

    Collection<Student> findByAgeBetween(int minAge, int maxAge);

    Student findStudentById(Long id);
}
