package com.university.security.uicontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeUIController {
    @GetMapping("/dean")
    public String dean()
    {
        return "deanHome";
    }
    @GetMapping("/faculty")
    public String faculty()
    {
        return "facultyHome";
    }
    @GetMapping("/student")
    public String studentHome()
    {
        return "studentHome";
    }
}
