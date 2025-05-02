package com.university.application.sections.controller;

import com.university.application.sections.entity.Section;
import com.university.application.sections.services.SectionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@Profile("sectionRunner")
@RequestMapping("/section")
public class SectionController{

    @Autowired
    SectionServices sectionServices;

    @PostMapping("/addSection")
    public String addSection(@RequestBody Section section)
    {
        boolean flag =  sectionServices.addSection(section);
        if(flag)
        {
            return "Section added successfully";
        }
        return "Unsuccessful";
    }
    @GetMapping("/getAllSection")
    public List<Section> getAllSection()
    {
        return sectionServices.getAllSection();
    }
    @GetMapping("/getSectionByCode/{sectionCode}")
    public Section getSectionByCode(@PathVariable("sectionCode") String sectionCode)
    {
        return sectionServices.getSectionByCode(sectionCode);
    }
    @DeleteMapping("/removeSectionByCode/{sectionCode}")
    public String removeSectionByCode(@PathVariable("sectionCode") String sectionCode)
    {
        if(sectionServices.removeSectionByCode(sectionCode)) {
            return sectionCode + " section removed";
        }
        return sectionCode + " section can't be removed";
    }

}
