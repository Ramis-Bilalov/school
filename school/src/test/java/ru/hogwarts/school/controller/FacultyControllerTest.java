package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(facultyService).isNotNull();
    }

    @Test
    public void createFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Fourth");
        faculty.setColor("orange");
        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void getFacultyInfo() throws Exception {
        long id = 1;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty" + "?id=" + id, String.class))
                .isNotNull();
    }

    @Test
    public void editFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Fourth");
        faculty.setColor("orange");
        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotNull();
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        long id = 1;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty" + "?id=" + id, String.class))
                .isNotNull();
    }

    @Test
    public void getFacultyByColorTest() throws Exception {
        String color = "orange";
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/by-color" + "?color=" + color, String.class))
                .isNotNull();
    }

    @Test
    public void getFacultyByNameTest() throws Exception {
        String name = "Fourth";
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/by-name" + "?name=" + name, String.class))
                .isNotNull();
    }

    @Test
    public void getAllStudentsByFaculty() throws Exception {
        String name = "Fourth";
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/get-student-faculty/" + name, String.class))
                .isNotNull();
    }
}
