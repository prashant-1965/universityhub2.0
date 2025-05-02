package com.university.application.branch.controller;

import com.university.application.branch.entity.Branch;
import com.university.application.branch.services.BranchServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@Profile("branchRunner")
@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {

    final BranchServices branchServices;

    @PostMapping("/addBranch")
    public String addBranch(@RequestBody Branch branch)
    {
        return branchServices.addBranch(branch)?"Branch added Successfully":"UnSuccessful";
    }
    @GetMapping("/getAllBranch")
    public List<Branch> getAllBranch()
    {
        return branchServices.getAllBranch();
    }
    @GetMapping("/getBranchByCode/{branchCode}")
    public Branch getBranchByCode(@PathVariable("branchCode") String branchCode)
    {
        return branchServices.getBranchByCode(branchCode);
    }
    @DeleteMapping("/deleteBranchByCode/{branchCode}")
    public String deleteBranchByCode(@PathVariable("branchCode") String branchCode)
    {
        boolean check = branchServices.deleteBranchByCode(branchCode);
        if(check)
        {
            return branchCode +" deleted Successfully";
        }
        return "UnSuccessful";
    }

}
