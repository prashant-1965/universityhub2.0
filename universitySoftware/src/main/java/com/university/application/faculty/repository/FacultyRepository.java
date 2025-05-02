package com.university.application.faculty.repository;

import com.university.application.faculty.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}
