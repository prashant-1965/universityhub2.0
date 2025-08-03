package com.university.application.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.application.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Commit
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddStudent200() throws Exception {
        Student student = new Student();student.setFirstName("dfdg");
        student.setLastName("asdsf");student.setEmail(".Xmail");
        student.setLocation("tt");

        String contentBody = objectMapper.writeValueAsString(student);
        mockMvc.perform(post("/student/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentBody))
                .andExpect(status().isOk());
    }
    @Test
    void testAddStudent400() throws Exception {
        Student student = new Student();

        String contentBody = objectMapper.writeValueAsString(student);
        mockMvc.perform(post("/student/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAllStudent200() throws Exception {
        mockMvc.perform(get("/student/allStudent"))
                .andExpect(status().isOk());
    }
    @Test
    void testAllStudent404Error() throws Exception {
        mockMvc.perform(get("/student/allStudent"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testStudentById200() throws Exception {
        long uid = 52;
        mockMvc.perform(get("/student/studentById/" + uid))
                .andExpect(status().isOk());
    }
    @Test
    void testStudentById404Error() throws Exception {
        long uid = 454L;
        mockMvc.perform(get("/student/studentById/" + uid))
                .andExpect(status().isNotFound());
    }
    @Test
    void testStudentById400Error() throws Exception {
        Long uid = null;
        mockMvc.perform(get("/student/studentById/"+uid))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRemoveStudent200() throws Exception {
        long uid = 52;
        mockMvc.perform(delete("/student/removeStudent/"+uid))
                .andExpect(status().isOk());
    }
    @Test
    void testRemoveStudent404Error() throws Exception {
        long uid = 34234L;
        mockMvc.perform(delete("/student/removeStudent/"+uid))
                .andExpect(status().isNotFound());
    }
    @Test
    void testRemoveStudent400Error() throws Exception {
        Long id = null;
        mockMvc.perform(delete("/student/removeStudent/"+id))
                .andExpect(status().isBadRequest());
    }
}
