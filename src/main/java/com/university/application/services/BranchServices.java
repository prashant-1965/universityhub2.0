package com.university.application.services;

import com.university.application.entity.Branch;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BranchServices {
    public String addBranch(Branch branch);
    public List<Branch> getAllBranch();
    public Branch getBranchByCode(String branchCode);
    public boolean deleteBranchByCode(String branchCode);
}
