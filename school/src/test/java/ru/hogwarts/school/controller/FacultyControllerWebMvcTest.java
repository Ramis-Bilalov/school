package ru.hogwarts.school.controller;

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
import ru.hogwarts.school.repository.FacultyRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacultyRepository facultyRepository;

    @MockitoBean
    private AvatarRepository avatarRepository;

    @MockitoBean
    private StudentController studentController;

    @MockitoBean
    private AvatarService avatarService;
    @MockitoBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void createFacultyTest() throws Exception {
        String facultyName = "First";
        String facultyColor = "white";
        long id = 40;

        Faculty faculty = new Faculty(); // Можно заполнить любыми значениями
        faculty.setId(id);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);

        JSONObject facultyObj = new JSONObject();
        facultyObj.put("id", id);
        facultyObj.put("name", facultyName);
        facultyObj.put("color", facultyColor);

        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObj.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(facultyName))
                .andExpect(jsonPath("$.color").value(facultyColor));

    }

    @Test
    public void getFacultyInfoTest() throws Exception {
        String facultyName = "First";
        String facultyColor = "white";
        long id = 40;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);

        when(facultyService.findFaculty(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(facultyName))
                .andExpect(jsonPath("$.color").value(facultyColor));
    }

    @Test
    public void editFacultyTest() throws Exception {
        String facultyName = "First";
        String facultyColor = "white";
        long id = 40;

        Faculty faculty = new Faculty(); // Можно заполнить любыми значениями
        faculty.setId(id);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);

        JSONObject studentObj = new JSONObject();
        studentObj.put("id", id);
        studentObj.put("name", facultyName);
        studentObj.put("age", facultyColor);

        when(facultyService.editFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(studentObj.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(facultyName))
                .andExpect(jsonPath("$.color").value(facultyColor));
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        long id = 41;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getFacultyByColorTest() throws Exception {
        String facultyName = "First";
        String facultyColor = "white";
        long id = 40;

        Faculty faculty = new Faculty(); // Можно заполнить любыми значениями
        faculty.setId(id);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);

        Collection<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);

        when(facultyService.getAllFaculties()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/by-color?color=" + facultyColor)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(id))
                .andExpect(jsonPath("$.[0].name").value(facultyName))
                .andExpect(jsonPath("$.[0].color").value(facultyColor));
    }

    @Test
    public void getFacultyByNameTest() throws Exception {
        String facultyName = "First";
        String facultyColor = "white";
        long id = 40;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);

        when(facultyService.getFacultyByName(any(String.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/by-name?name=" + facultyName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(facultyName))
                .andExpect(jsonPath("$.color").value(facultyColor));
    }

    @Test
    public void getAllStudentsByFacultyTest() throws Exception {
        String facultyName = "First";
        String facultyColor = "white";
        long id = 40;

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

        when(facultyService.getFacultyStudents(any(String.class))).thenReturn(studentByMinAndMaxAge);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/get-faculty-students/" + facultyName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(studentByMinAndMaxAge.size())));
    }
}
