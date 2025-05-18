package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public List<String> getAllStudentsNamesStartWithA() {
        List<String> studentNamesStartWithA = studentRepository.findAll()
                .stream()
                .filter(student -> student.getName().startsWith("A"))
                .map(student -> student.getName().toUpperCase())
                .toList();
        return studentNamesStartWithA;
    }

    public double getAverageStudentAge() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .getAsDouble();
    }

    public List<String> getAllStudentsNames() {
        List<String> studentNames = studentRepository.findAll()
                .stream()
                .map(student -> student.getName().toUpperCase())
                .toList();
        return studentNames;
    }

    public int getAllStudentsCount() {
        logger.info("Was invoked method for get all students count");
        return studentRepository.getAllStudentsCount();
    }
    public int getAverageStudentsAge() {
        logger.info("Was invoked method for get average students age");
        return studentRepository.getAverageStudentsAge();
    }

    public Collection<Student> getFiveLastStudentsOrderById() {
        logger.info("Was invoked method for get last five students ordered by id");
        return studentRepository.getFiveLastStudentsOrderById();
    }
    public Student findStudent(long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }
    public Collection<Student> getAllStudentsAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for get all students age between");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public List<Student> getStudentByName(String name) {
        logger.info("Was invoked method for get student by name");
        return studentRepository.getStudentsByName(name);
    }

    public Faculty getStudentFaculty(long id) {
        Student student = studentRepository.findById(id).get();
        logger.info("Was invoked method for get student faculty");
        return student.getFaculty();
    }
}
