package com.university.application.sections.services;

import com.university.application.sections.entity.Section;
import com.university.application.sections.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServicesImpl implements SectionServices{
    @Autowired
    SectionRepository sectionRepository;

    public boolean addSection(Section section)
    {
        Section section1 =  sectionRepository.save(section);
        return section1.getId()!=null;
    }
    public List<Section> getAllSection()
    {
        return sectionRepository.findAll();
    }
    public Section getSectionByCode(String sectionCode)
    {
        Optional<Section> optionalSection =  sectionRepository.findBySectionCode(sectionCode);
        return optionalSection.orElse(null);
    }

    @Transactional
    public boolean removeSectionByCode(String sectionCode)
    {
        if(sectionRepository.existsBySectionCode(sectionCode)) {
            sectionRepository.deleteBySectionCode(sectionCode);
            return true;
        }
        return false;
    }
}
