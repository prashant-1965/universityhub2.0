package com.university.security.uicontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUpUIController {
    @GetMapping("/signUp")
    public String signUp()
    {
        return "signUp";
    }
}
