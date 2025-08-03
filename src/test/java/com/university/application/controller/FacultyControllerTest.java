package com.university.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.application.entity.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Commit
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addFaculty200() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setFirstName("xyz");faculty.setDesignation("HOD");
        faculty.setLastName("sadf");faculty.setLocation("asdasf");
        faculty.setEmail("@gmail");
        mockMvc.perform(post("/faculty/addFaculty").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty))).andExpect(status().isOk());
    }
    @Test
    public void addFaculty400() throws Exception {
        Faculty faculty = new Faculty();
        mockMvc.perform(post("/faculty/addFaculty").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAllFaculty200() throws Exception {
        mockMvc.perform(get("/faculty/viewAll"))
                .andExpect(status().isOk());
    }
    @Test
    void testAllFaculty404Error() throws Exception {
        mockMvc.perform(get("/faculty/viewAll"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testFacultyById200() throws Exception {
        long uid = 1;
        mockMvc.perform(get("/faculty/findByUid/" + uid))
                .andExpect(status().isOk());
    }
    @Test
    void testFacultyById404Error() throws Exception {
        long uid = 454L;
        mockMvc.perform(get("/faculty/findByUid/" + uid))
                .andExpect(status().isNotFound());
    }
    @Test
    void testFacultyById400Error() throws Exception {
        Long uid = null;
        mockMvc.perform(get("/faculty/findByUid/"+uid))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRemoveFaculty200() throws Exception {
        long uid = 1;
        mockMvc.perform(delete("/faculty/removeByUid/"+uid))
                .andExpect(status().isOk());
    }
    @Test
    void testRemoveFaculty404Error() throws Exception {
        long uid = 34234L;
        mockMvc.perform(delete("/faculty/removeByUid/"+uid))
                .andExpect(status().isNotFound());
    }
    @Test
    void testRemoveFaculty400Error() throws Exception {
        Long id = null;
        mockMvc.perform(delete("/faculty/removeByUid/"+id))
                .andExpect(status().isBadRequest());
    }
}
