package com.university.security.uicontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/branchUi")
public class BranchUIController {

    @GetMapping("/viewAll")
    public String viewAllBranchesPage() {
        return "branchDirectory";
    }
    @GetMapping("/indexBranch")
    public String indexBranch() {
        return "indexBranch";
    }
}
