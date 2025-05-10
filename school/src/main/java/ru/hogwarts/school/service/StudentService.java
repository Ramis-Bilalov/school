package ru.hogwarts.school.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public int getAllStudentsCount() {
        return studentRepository.getAllStudentsCount();
    }
    public int getAverageStudentsAge() {
        return studentRepository.getAverageStudentsAge();
    }

    public Collection<Student> getFiveLastStudentsOrderById() {
        return studentRepository.getFiveLastStudentsOrderById();
    }
    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Collection<Student> getAllStudentsAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public List<Student> getStudentByName(String name) {
        return studentRepository.getStudentsByName(name);
    }

    public Faculty getStudentFaculty(long id) {
        Student student = studentRepository.findById(id).get();
        return student.getFaculty();
    }
}
