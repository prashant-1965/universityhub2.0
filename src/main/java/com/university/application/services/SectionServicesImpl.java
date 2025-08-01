package com.university.application.services;

import com.university.application.entity.Section;
import com.university.application.exceptionclass.InvalidBranchException;
import com.university.application.exceptionclass.InvalidSectionException;
import com.university.application.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServicesImpl implements SectionServices {
    @Autowired
    SectionRepository sectionRepository;

    public String addSection(Section section) throws InvalidSectionException
    {
        if(section.getSectionCode()==null || section.getSectionInCharge()==null){
            throw new InvalidSectionException("section fields can not be null");
        }
        sectionRepository.save(section);
        return "Section added Successfully";
    }
    public List<Section> getAllSection() throws InvalidSectionException
    {
        List<Section> sectionList= sectionRepository.findAll();
        if(sectionList.isEmpty()){
            throw new InvalidSectionException("No section found");
        }
        return sectionList;
    }
    public Section getSectionByCode(String sectionCode) throws InvalidBranchException
    {
        if(sectionCode==null || sectionRepository.existsBySectionCode(sectionCode)){
            throw new InvalidBranchException("Please provide valid section code");
        }
        return sectionRepository.findBySectionCode(sectionCode);
    }

    @Transactional
    public String removeSectionByCode(String sectionCode) throws InvalidSectionException
    {
        if(sectionCode==null || sectionRepository.existsBySectionCode(sectionCode)){
            throw new InvalidBranchException("Please provide valid section code");
        }
        sectionRepository.deleteBySectionCode(sectionCode);
        return "Section removed Successfully";
    }
}
