package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable("name") String name) {
        List<Student> students = studentService.getStudentByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/get-student-names-start-with-a")
    public ResponseEntity<List<String>> getAllStudentsNamesStartWithA() {
        List<String> studentNamesStartWithA = studentService.getAllStudentsNamesStartWithA();
        return ResponseEntity.ok(studentNamesStartWithA);
    }

    @GetMapping("/get-average-age")
    public ResponseEntity<Double> getAverageAge() {
        double averageStudentAge = studentService.getAverageStudentAge();
        return ResponseEntity.ok(averageStudentAge);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        if (editStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editStudent);
    }
    @GetMapping("/get-all-students-count")
    public int getAllStudentsCount() {
        return studentService.getAllStudentsCount();
    }

    @GetMapping("/get-average-students-age")
    public int getAverageStudentsAge() {
        return studentService.getAverageStudentsAge();
    }

    @GetMapping("/get-last-students")
    public ResponseEntity<Collection<Student>> getFiveLastStudentsOrderById () {
        Collection<Student> lastStudents = studentService.getFiveLastStudentsOrderById();
        return ResponseEntity.ok(lastStudents);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("by-age")
    public List<Student> getStudentsByAge(@RequestParam int age) {
        return studentService.getAllStudents().stream().filter(student -> student.getAge() == age).toList();
    }
    @GetMapping("age-between")
    public Collection<Student> findStudentsByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.getAllStudentsAgeBetween(minAge, maxAge);
    }

    @GetMapping("get-student-faculty")
    public Faculty getStudentFaculty(@RequestParam Long id) {
        return studentService.getStudentFaculty(id);
    }
}
