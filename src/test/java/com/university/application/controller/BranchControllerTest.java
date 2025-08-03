package com.university.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.application.entity.Branch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Commit
public class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddBranch200() throws Exception {
        Branch branch = new Branch();
        branch.setBranchCode("ECE04");
        branch.setBranchName("Computer");
        branch.setBranchHod("Dr. Iyers");

        String jsonBody = objectMapper.writeValueAsString(branch);

        mockMvc.perform(post("/branch/addBranch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
    }
    @Test
    void testAddBranch400() throws Exception {
        Branch branch = new Branch();

        String jsonBody = objectMapper.writeValueAsString(branch);

        mockMvc.perform(post("/branch/addBranch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllBranches200() throws Exception {
        mockMvc.perform(get("/branch/getAllBranch"))
                .andExpect(status().isOk());
    }
    @Test
    void testGetAllBranch404() throws Exception{
        mockMvc.perform(get("/branch/getAllBranch"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetBranchByCode() throws Exception {
        String branchCode = "ECE04";
        mockMvc.perform(get("/branch/getBranchByCode/" + branchCode))
                .andExpect(status().isOk());
    }
    @Test
    void testGetBranchByCode404() throws Exception {
        String branchCode = "ECE01";
        mockMvc.perform(get("/branch/getBranchByCode/" + branchCode))
                .andExpect(status().isNotFound());
    }
    @Test
    void testGetBranchByCode400() throws Exception {
        String branchCode = null;
        mockMvc.perform(get("/branch/getBranchByCode/" + branchCode))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteBranchByCode() throws Exception {
        String branchCode = "ECE02";
        mockMvc.perform(delete("/branch/deleteBranchByCode/" + branchCode))
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteBranchByCode404() throws Exception {
        String branchCode = "ECE01";
        mockMvc.perform(delete("/branch/deleteBranchByCode/" + branchCode))
                .andExpect(status().isNotFound());
    }
    @Test
    void testDeleteBranchByCode400() throws Exception {
        String branchCode = null;
        mockMvc.perform(delete("/branch/deleteBranchByCode/" + branchCode))
                .andExpect(status().isBadRequest());
    }
}
