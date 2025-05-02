package com.university.application.branch.services;

import com.university.application.branch.repository.BranchRepository;
import com.university.application.branch.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServicesImpl implements BranchServices{
    @Autowired
    public BranchRepository branchRepository;
    public boolean addBranch(Branch branch)
    {
        Branch savedBranch = branchRepository.save(branch);
        return savedBranch.getId() != null;
    }
    public List<Branch> getAllBranch()
    {
        List<Branch> list =  branchRepository.findAll();
        return list;
    }
    public Branch getBranchByCode(String branchCode)
    {
        Optional<Branch> optionalBranch = branchRepository.findByBranchCode(branchCode);
        return optionalBranch.orElse(null);
    }

    @Transactional
    public boolean deleteBranchByCode(String branchCode)
    {
        if(branchRepository.existsByBranchCode(branchCode))
        {
            branchRepository.deleteByBranchCode(branchCode);
            return true;
        }
        return  false;
    }
}
