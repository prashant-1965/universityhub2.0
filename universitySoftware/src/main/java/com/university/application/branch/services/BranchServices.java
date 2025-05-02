package com.university.application.branch.services;

import com.university.application.branch.entity.Branch;

import java.util.List;

public interface BranchServices {
    public boolean addBranch(Branch branch);
    public List<Branch> getAllBranch();
    public Branch getBranchByCode(String branchCode);
    public boolean deleteBranchByCode(String branchCode);
}
