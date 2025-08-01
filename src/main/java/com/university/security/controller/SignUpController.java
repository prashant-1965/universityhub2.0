package com.university.security.controller;


import com.university.security.entity.Credentials;
import com.university.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    UserService userService;

    @PostMapping("/addCredentials")
    public ResponseEntity<String> addCredentials(@RequestBody Credentials credentials){
        return ResponseEntity.status(200).body(userService.addCredentials(credentials));
    }
}
