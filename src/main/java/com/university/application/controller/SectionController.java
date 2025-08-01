package com.university.application.controller;

import com.university.application.entity.Section;
import com.university.application.services.SectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/section")
public class SectionController{

    @Autowired
    SectionServices sectionServices;

    @PostMapping("/addSection")
    public ResponseEntity<?> addSection(@RequestBody Section section){
        return ResponseEntity.status(200).body(sectionServices.addSection(section));
    }
    @GetMapping("/getAllSection")
    public ResponseEntity<?> getAllSection(){
        return ResponseEntity.status(200).body(sectionServices.getAllSection());
    }
    @GetMapping("/getSectionByCode/{sectionCode}")
    public ResponseEntity<?> getSectionByCode(@PathVariable("sectionCode") String sectionCode){
        return ResponseEntity.status(200).body(sectionServices.getSectionByCode(sectionCode));
    }
    @DeleteMapping("/removeSectionByCode/{sectionCode}")
    public ResponseEntity<?> removeSectionByCode(@PathVariable("sectionCode") String sectionCode){
        return ResponseEntity.status(200).body(sectionServices.removeSectionByCode(sectionCode));
    }

}
