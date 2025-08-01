package com.university.application.controller;

import com.university.application.entity.Branch;
import com.university.application.services.BranchServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    BranchServices branchServices;
    @PostMapping("/addBranch")
    public ResponseEntity<?> addBranch(@RequestBody Branch branch)
    {
        return ResponseEntity.status(200).body(branchServices.addBranch(branch));
    }
    @GetMapping("/getAllBranch")
    public ResponseEntity<?> getAllBranch()
    {
        return ResponseEntity.status(200).body(branchServices.getAllBranch());
    }
    @GetMapping("/getBranchByCode/{BranchCode}")
    public ResponseEntity<?> getBranchByCode(@PathVariable("BranchCode") String BranchCode)
    {
        return ResponseEntity.status(200).body(branchServices.getBranchByCode(BranchCode));
    }
    @DeleteMapping("/deleteBranchByCode/{BranchCode}")
    public ResponseEntity<?> deleteBranchByCode(@PathVariable("BranchCode") String BranchCode)
    {
        return ResponseEntity.status(200).body(branchServices.deleteBranchByCode(BranchCode));
    }

}
