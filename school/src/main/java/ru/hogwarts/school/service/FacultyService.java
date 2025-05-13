package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    private Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public String getLongestFacultyName() {
        String facultyName = facultyRepository.findAll()
                .stream()
                .map(faculty -> faculty.getName())
                .sorted()
                .findFirst()
                .get();
        return facultyName;
    }


    public Faculty findFaculty(long id) {
        logger.info("Was invoked method for find faculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.info("Was invoked method for get all faculties");
        return facultyRepository.findAll();
    }

    public Faculty getFacultyByName(String name) {
        logger.info("Was invoked method for get faculty by name");
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    public Collection<Student> getFacultyStudents(String name) {
        Faculty faculty = facultyRepository.findFacultyByNameIgnoreCase(name);
        logger.info("Was invoked method for get students of faculty");
        return faculty.getStudents();
    }
}
