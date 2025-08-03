package com.university.application.services;

import com.university.application.exceptionclass.InvalidBranchException;
import com.university.application.repository.BranchRepository;
import com.university.application.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BranchServicesImpl implements BranchServices {
    @Autowired
    public BranchRepository branchRepository;
    public String addBranch(Branch branch) throws InvalidBranchException
    {
        if(branch==null|| branch.getBranchCode()==null || branch.getBranchName()==null || branch.getBranchHod()==null){
            throw new InvalidBranchException("Invalid Branch Details", HttpStatus.BAD_REQUEST);
        }
        branchRepository.save(branch);
        return "Successfully";
    }
    public List<Branch> getAllBranch() throws InvalidBranchException
    {
        List<Branch> list = branchRepository.findAll();
        if(list.isEmpty()){
            throw new InvalidBranchException("No branch Available",HttpStatus.NOT_FOUND);
        }
        return list;
    }
    public Branch getBranchByCode(String branchCode) throws InvalidBranchException
    {
        if(branchCode.equals("null")){
            throw new InvalidBranchException("Branch Code should not be empty",HttpStatus.BAD_REQUEST);
        }
        if(!branchRepository.existsByBranchCode(branchCode)){
            throw new InvalidBranchException("Invalid Branch Code",HttpStatus.NOT_FOUND);
        }
        Optional<Branch> optionalBranch = branchRepository.findByBranchCode(branchCode);
        return optionalBranch.orElse(null);
    }

    @Transactional
    public String deleteBranchByCode(String branchCode) throws InvalidBranchException
    {
        if(branchCode.equals("null")){
            throw new InvalidBranchException("Branch Code should not be empty",HttpStatus.BAD_REQUEST);
        }
        if(!branchRepository.existsByBranchCode(branchCode)){
            throw new InvalidBranchException("Invalid Branch Code",HttpStatus.NOT_FOUND);
        }
        branchRepository.deleteByBranchCode(branchCode);
        return "Branch Removed Successfully";
    }
}
