package com.university.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.application.entity.Branch;
import com.university.application.entity.Section;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Commit
@ActiveProfiles("test")
public class SectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddSection200() throws Exception {
        Section section = new Section();
        section.setSectionCode("K2233");section.setSectionInCharge("xyz");

        String jsonBody = objectMapper.writeValueAsString(section);

        mockMvc.perform(post("/section/addSection")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
    }
    @Test
    void testAddSection400() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(new Section());

        mockMvc.perform(post("/section/addSection")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllSection200() throws Exception {
        mockMvc.perform(get("/section/getAllSection"))
                .andExpect(status().isOk());
    }
//    @Test
//    void testGetAllSection404() throws Exception{
//        mockMvc.perform(get("/section/getAllSection"))
//                .andExpect(status().isNotFound());
//    }

    @Test
    void testGetSectionByCode() throws Exception {
        String sectionCode = "K2232";
        mockMvc.perform(get("/section/getSectionByCode/" + sectionCode))
                .andExpect(status().isOk());
    }
    @Test
    void testGetSectionByCode404() throws Exception {
        String sectionCode = "k2234";
        mockMvc.perform(get("/section/getSectionByCode/" + sectionCode))
                .andExpect(status().isNotFound());
    }
    @Test
    void testGetSectionByCode400() throws Exception {
        String sectionCode = null;
        mockMvc.perform(get("/section/getSectionByCode/" + sectionCode))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteSectionByCode() throws Exception {
        String sectionCode = "k2232";
        mockMvc.perform(delete("/section/removeSectionByCode/" + sectionCode))
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteSectionByCode404() throws Exception {
        String sectionCode = "k2229";
        mockMvc.perform(delete("/section/removeSectionByCode/" + sectionCode))
                .andExpect(status().isNotFound());
    }
    @Test
    void testDeleteSectionByCode400() throws Exception {
        String sectionCode = null;
        mockMvc.perform(delete("/section/removeSectionByCode/" + sectionCode))
                .andExpect(status().isBadRequest());
    }
}
