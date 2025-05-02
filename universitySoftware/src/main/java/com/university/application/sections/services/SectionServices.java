package com.university.application.sections.services;

import com.university.application.sections.entity.Section;
import java.util.List;


public interface SectionServices {
    boolean addSection(Section section);
    List<Section> getAllSection();
    Section getSectionByCode(String sectionCode);
    boolean removeSectionByCode(String sectionCode);
}
