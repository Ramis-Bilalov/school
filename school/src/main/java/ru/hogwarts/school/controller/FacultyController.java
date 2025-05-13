package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;


    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/get-longest-faculty-name")
    public String getLongestFacultyName() {
        return facultyService.getLongestFacultyName();
    }

    @GetMapping("/get-sum")
    public long getSum() {
        long sum = LongStream
                .iterate(1, a -> a +1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        return sum;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty);
        if (editFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("by-color")
    public List<Faculty> getFacultiesByColor(@RequestParam String color) {
        return facultyService.getAllFaculties().stream().filter(faculty -> faculty.getColor().equals(color)).toList();
    }

    @GetMapping("by-name")
    public Faculty getFacultyByName(@RequestParam String name) {
        return facultyService.getFacultyByName(name);
    }

    @GetMapping("get-faculty-students/{name}")
    public Collection<Student> getAllStudentsByFaculty(@PathVariable String name) {
        return facultyService.getFacultyStudents(name);
    }
}
