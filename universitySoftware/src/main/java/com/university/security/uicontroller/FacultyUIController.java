package com.university.security.uicontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facultyUi")
public class FacultyUIController {
    @GetMapping("/viewAll")
    public String viewAll()
    {
        return "facultyDirectory";
    }
    @GetMapping("/indexFaculty")
    public String indexFaculty()
    {
        return "indexFaculty";
    }
}
