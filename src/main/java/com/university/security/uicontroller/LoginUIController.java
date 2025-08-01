package com.university.security.uicontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginUIController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
