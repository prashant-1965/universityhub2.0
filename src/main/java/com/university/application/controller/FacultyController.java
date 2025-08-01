package com.university.application.controller;


import com.university.application.entity.Faculty;
import com.university.application.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @PostMapping("/addFaculty")
    public ResponseEntity<?> addFaculty(@RequestBody Faculty faculty)
    {

        return ResponseEntity.status(200).body(facultyService.addFaculty(faculty));
    }
    @GetMapping("/viewAll")
    public ResponseEntity<?> viewAllFaculty()
    {
        return ResponseEntity.status(200).body(facultyService.getAllFaculty());
    }
    @GetMapping("/findByUid/{uid}")
    public ResponseEntity<?> findByUid(@PathVariable("uid") Long uid)
    {
        return ResponseEntity.status(200).body(facultyService.getFacultyById(uid));
    }
    @DeleteMapping("removeByUid/{uid}")
    public ResponseEntity<?> removeByUid(@PathVariable("uid") Long uid)
    {
        return ResponseEntity.status(200).body(facultyService.removeFacultyById(uid));
    }
}
