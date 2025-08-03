package com.university.application.services;

import com.university.application.entity.Branch;

import java.util.List;

public interface BranchServices {
    public String addBranch(Branch branch);
    public List<Branch> getAllBranch();
    public Branch getBranchByCode(String branchCode);
    public String deleteBranchByCode(String branchCode);
}
