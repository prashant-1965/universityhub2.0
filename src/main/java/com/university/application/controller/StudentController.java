package com.university.application.controller;

import com.university.application.entity.Student;
import com.university.application.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
public class StudentController{
    @Autowired
    private StudentServices studentServices;

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        return ResponseEntity.status(200).body(studentServices.addStudent(student));
    }
    @GetMapping("/allStudent")
    public ResponseEntity<?> allStudent(){
        return ResponseEntity.status(200).body(studentServices.allStudent());
    }
    @GetMapping("/studentById/{id}")
    public ResponseEntity<?> studentById(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(studentServices.studentById(id));
    }
    @DeleteMapping("removeStudent/{id}")
    public ResponseEntity<?> removeStudent(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(studentServices.removeStudent(id));
    }

}
