package com.university.application.faculty.controller;

import com.university.application.faculty.entity.Faculty;
import com.university.application.faculty.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @PostMapping("/addFaculty")
    public String addFaculty(@RequestBody Faculty faculty)
    {
        return facultyService.addFaculty(faculty)?"Faculty added Successfully":"UnSuccessful";
    }
    @GetMapping("/viewAll")
    public List<Faculty> viewAllFaculty()
    {
        return facultyService.getAllFaculty();
    }
    @GetMapping("/findByUid/{uid}")
    public Faculty findByUid(@PathVariable("uid") Long uid)
    {
        return facultyService.getFacultyById(uid);
    }
    @DeleteMapping("removeByUid/{uid}")
    public String removeByUid(@PathVariable("uid") Long uid)
    {
        if(facultyService.removeFacultyById(uid))
        {
            return uid+" has removed SuccessFully";
        }
        return uid+" does not exist";
    }
}
