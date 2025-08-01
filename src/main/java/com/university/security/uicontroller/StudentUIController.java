package com.university.security.uicontroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/studentUi")
public class StudentUIController {
    @GetMapping("/viewAll")
    public String viewAllStudent()
    {
        return "studentDirectory";
    }
    @GetMapping("/indexStudent")
    public String indexStudent()
    {
        return "indexStudent";
    }

}
