package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentRepository studentRepository;

    @MockitoBean
    private AvatarRepository avatarRepository;

    @MockitoBean
    private FacultyService facultyService;

    @MockitoBean
    private StudentService studentService;

    @MockitoBean
    private AvatarService avatarService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void createStudentTest() throws Exception {
        String studentName = "Ramil";
        int studentAge = 31;
        long id = 40;
        String facultyName = "first";
        String facultyColor = "yellow";
        long faculty_id = 1;

        Student newStudent = new Student();
        Faculty faculty = new Faculty();
        faculty.setId(faculty_id);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);

        newStudent.setId(id);
        newStudent.setName(studentName);
        newStudent.setAge(studentAge);
        newStudent.setFaculty(faculty);

        JSONObject studentObj = new JSONObject();
        studentObj.put("id", id);
        studentObj.put("name", studentName);
        studentObj.put("age", studentAge);
        studentObj.put("faculty_id", faculty_id);


        when(studentService.createStudent(any(Student.class))).thenReturn(newStudent);
        when(studentService.findStudent(any(Long.class))).thenReturn(newStudent);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObj.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(studentName))
                .andExpect(jsonPath("$.age").value(studentAge));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(studentName))
                .andExpect(jsonPath("$.age").value(studentAge));

    }

    @Test
    public void getStudentsInfoTest() throws Exception {
        String studentName = "Igor";
        int studentAge = 40;
        long id = 41;

        Student student = new Student();
        student.setId(id);
        student.setName(studentName);
        student.setAge(studentAge);

        when(studentService.findStudent(any(Long.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(studentName))
                .andExpect(jsonPath("$.age").value(studentAge));
    }

    @Test
    public void editStudentTest() throws Exception {
        String studentName = "Igor";
        int studentAge = 40;
        long id = 41;
        long faculty_id = 1;

        Student student = new Student();
        student.setId(id);
        student.setName(studentName);
        student.setAge(studentAge);

        JSONObject studentObj = new JSONObject();
        studentObj.put("id", id);
        studentObj.put("name", studentName);
        studentObj.put("age", studentAge);
        studentObj.put("faculty_id", faculty_id);

        when(studentService.editStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(studentObj.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(studentName))
                .andExpect(jsonPath("$.age").value(studentAge));
    }

    @Test
    public void deleteStudentTest() throws Exception {
        long id = 41;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getStudentsByAgeTest() throws Exception {
        String studentName = "Igor";
        int studentAge = 40;
        long id = 41;

        Student student = new Student();
        student.setId(id);
        student.setName(studentName);
        student.setAge(studentAge);

        Collection<Student> students = new ArrayList<>();
        students.add(student);

        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/by-age?age=" + studentAge)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(id))
                .andExpect(jsonPath("$.[0].name").value(studentName))
                .andExpect(jsonPath("$.[0].age").value(studentAge));
    }

    @Test
    public void findStudentsByAgeBetweenTest() throws Exception {
        int minAge = 30;
        int maxAge = 40;

        Student student1 = new Student("Igor", 31);
        student1.setId(60L);
        Student student2 = new Student("Konstantin", 33);
        student2.setId(61L);
        Student student3 = new Student("Max", 35);
        student2.setId(62L);
        Student student4 = new Student("Alex", 39);
        student2.setId(63L);

        Collection<Student> studentByMinAndMaxAge = new ArrayList<>();
        studentByMinAndMaxAge.add(student1);
        studentByMinAndMaxAge.add(student2);
        studentByMinAndMaxAge.add(student3);
        studentByMinAndMaxAge.add(student4);

        when(studentService.getAllStudentsAgeBetween(minAge, maxAge)).thenReturn(studentByMinAndMaxAge);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age-between")
                        .param("minAge", Integer.toString(minAge))
                        .param("maxAge", Integer.toString(maxAge))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(studentByMinAndMaxAge.size())));
    }

    @Test
    public void getStudentFacultyTest() throws Exception {
        long id = 12L;
        String facultyName = "Fifth";
        String facultyColor = "Purple";

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);


        when(studentService.getStudentFaculty(id)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/get-student-faculty?id=" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(facultyName))
                .andExpect(jsonPath("$.color").value(facultyColor));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
