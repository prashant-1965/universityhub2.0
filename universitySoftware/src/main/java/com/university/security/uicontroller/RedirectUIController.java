package com.university.security.uicontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectUIController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
}
