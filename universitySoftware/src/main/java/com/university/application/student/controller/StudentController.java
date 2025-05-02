package com.university.application.student.controller;

import com.university.application.student.entity.Student;
import com.university.application.student.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Profile("studentRunner")
@RestController
@RequestMapping("/student")
public class StudentController{
    @Autowired
    private  StudentServices studentServices;
    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student)
    {
        if(studentServices.addStudent(student)) {
            return "Student Added Successfully";
        }
        else
        {
            return "Addition Failed";
        }
    }
    @GetMapping("/allStudent")
    public List<Student> allStudent()
    {
        return  studentServices.allStudent();
    }
    @GetMapping("/studentById/{id}")
    public Student studentById(@PathVariable("id") Long id)
    {
        return studentServices.studentById(id);
    }
    @DeleteMapping("removeStudent/{id}")
    public String removeStudent(@PathVariable("id") Long id)
    {
        if(studentServices.removeStudent(id)) {
            return "Student with " + id + " has removed SuccessFully";
        }
        else
        {
            return "Student is not available/can not be removed";
        }
    }

}
