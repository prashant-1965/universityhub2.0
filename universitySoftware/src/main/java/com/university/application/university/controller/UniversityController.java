package com.university.application.university.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
//@Profile("universityRunner")
public class UniversityController implements CommandLineRunner {
    public void run(String...args)
    {
        System.out.println("********************************************* Welcome to Bhardwaj Group of Institute **********************************************");
    }
}
