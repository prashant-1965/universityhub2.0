package com.university.application.services;

import com.university.application.entity.Section;
import java.util.List;


public interface SectionServices {
    String addSection(Section section);
    List<Section> getAllSection();
    Section getSectionByCode(String sectionCode);
    String removeSectionByCode(String sectionCode);
}
