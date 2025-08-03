package com.university.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.application.entity.Branch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddBranch() throws Exception {
        Branch branch = new Branch();
        branch.setBranchCode("ECE01");
        branch.setBranchName("Electronics");
        branch.setBranchHod("Dr. Iyer");

        String jsonBody = objectMapper.writeValueAsString(branch);

        mockMvc.perform(post("/branch/addBranch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllBranches() throws Exception {
        mockMvc.perform(get("/branch/getAllBranch"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBranchByCode() throws Exception {
        String branchCode = "ECE01"; // Make sure this exists before running
        mockMvc.perform(get("/branch/getBranchByCode/" + branchCode))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBranchByCode() throws Exception {
        String branchCode = "ECE01"; // Make sure this exists before running
        mockMvc.perform(delete("/branch/deleteBranchByCode/" + branchCode))
                .andExpect(status().isOk());
    }
}
