package com.university.security.uicontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sectionUi")
public class SectionUIController {
    @GetMapping("/indexSection")
    public String indexSection()
    {
        return "indexSection";
    }
    @GetMapping("/viewAll")
    public String branchDirectory()
    {
        return "sectionDirectory";
    }
}
