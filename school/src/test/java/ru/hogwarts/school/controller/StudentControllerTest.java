package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentService).isNotNull();
    }

    @Test
    public void createStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Igor");
        student.setAge(25);
        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void getStudentInfo() throws Exception {
        long id = 1;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + "id=" + id, String.class))
                .isNotNull();
    }

    @Test
    public void editStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Igor");
        student.setAge(25);
        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void deleteStudentTest() throws Exception {
        long id = 1;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + "?id=" + id, String.class))
                .isNotNull();
    }

    @Test
    public void getStudentByAgeTest() throws Exception {
        int age = 25;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/by-age" + "?age=" + age, String.class))
                .isNotNull();
    }

    @Test
    public void findStudentsByAgeBetweenTest() throws Exception {
        int minAge = 25;
        int maxAge = 41;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/age-between" + "?minAge=" + minAge + "&maxAge=" + maxAge, String.class))
                .isNotNull();
    }

    @Test
    public void getStudentFacultyTest() throws Exception {
        long id = 1;
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/get-student-faculty" + "?id=" + id, String.class))
                .isNotNull();
    }
}
