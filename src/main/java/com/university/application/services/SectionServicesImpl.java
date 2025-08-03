package com.university.application.services;

import com.university.application.entity.Section;
import com.university.application.exceptionclass.InvalidBranchException;
import com.university.application.exceptionclass.InvalidSectionException;
import com.university.application.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if(section==null || section.getSectionCode()==null || section.getSectionInCharge()==null){
            throw new InvalidSectionException("section fields can not be null", HttpStatus.BAD_REQUEST);
        }
        sectionRepository.save(section);
        return "Section added Successfully";
    }
    public List<Section> getAllSection() throws InvalidSectionException
    {
        List<Section> sectionList= sectionRepository.findAll();
        if(sectionList.isEmpty()){
            throw new InvalidSectionException("No section found",HttpStatus.NOT_FOUND);
        }
        return sectionList;
    }
    public Section getSectionByCode(String sectionCode) throws InvalidBranchException
    {
        if(sectionCode.equals("null")){
            throw new InvalidSectionException("Section can't be null",HttpStatus.BAD_REQUEST);
        }
        if(!sectionRepository.existsBySectionCode(sectionCode)){
            throw new InvalidBranchException("Section code is not found",HttpStatus.NOT_FOUND);
        }
        return sectionRepository.findBySectionCode(sectionCode);
    }

    @Transactional
    public String removeSectionByCode(String sectionCode) throws InvalidSectionException
    {
        if(sectionCode.equals("null")){
            throw new InvalidSectionException("Section can't be null",HttpStatus.BAD_REQUEST);
        }
        if(!sectionRepository.existsBySectionCode(sectionCode)){
            throw new InvalidBranchException("Section code is not available",HttpStatus.NOT_FOUND);
        }
        sectionRepository.deleteBySectionCode(sectionCode);
        return "Section removed Successfully";
    }
}
