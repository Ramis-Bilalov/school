package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findFacultyByNameIgnoreCase(String name);
    List<Faculty> findAll();
}
