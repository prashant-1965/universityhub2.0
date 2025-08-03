package com.university.application.services;

import com.university.application.exceptionclass.InvalidBranchException;
import com.university.application.repository.BranchRepository;
import com.university.application.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(branch.getBranchCode()==null || branch.getBranchName()==null || branch.getBranchHod()==null){
            throw new InvalidBranchException("Please fill required Details");
        }else{
            branchRepository.save(branch);
            return "Successfully";
        }
    }
    public List<Branch> getAllBranch() throws InvalidBranchException
    {
        List<Branch> list = branchRepository.findAll();
        if(list.isEmpty()){
            throw new InvalidBranchException("No branch Available");
        }
        return list;
    }
    public Branch getBranchByCode(String branchCode) throws InvalidBranchException
    {
        if(branchCode==null){
            throw new InvalidBranchException("Branch Code should not be empty");
        }
        Optional<Branch> optionalBranch = branchRepository.findByBranchCode(branchCode);
        return optionalBranch.orElse(null);
    }

    @Transactional
    public boolean deleteBranchByCode(String branchCode) throws InvalidBranchException
    {
        if(branchCode==null){
            throw new InvalidBranchException("Branch Code should not be empty");
        }
        if(branchRepository.existsByBranchCode(branchCode))
        {
            branchRepository.deleteByBranchCode(branchCode);
            return true;
        }
        return  false;
    }
}
