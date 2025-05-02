package com.university.security.controller;

import com.university.security.entity.Credentials;
import com.university.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    UserService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/addCredentials")
    public String addCredentials(@RequestBody Credentials credentials)
    {
        if(userDetailsService.existByUsername(credentials.getUsername()))
        {
            return "Username already exist!";
        }
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        if(userDetailsService.addCredentials(credentials))
        {
            return "Account Created Successfully";
        }
        return  "Can not create account!";
    }
}
