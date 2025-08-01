package com.university.application.repository;

import com.university.application.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long>
{
    Section findBySectionCode(String sectionCode);
    boolean existsBySectionCode(String sectionCode);
    void deleteBySectionCode(String sectionCode);
}
