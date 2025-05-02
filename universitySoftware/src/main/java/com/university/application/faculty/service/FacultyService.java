package com.university.application.faculty.service;

import com.university.application.faculty.entity.Faculty;

import java.util.List;

public interface FacultyService {
    boolean addFaculty(Faculty faculty);
    List<Faculty> getAllFaculty();
    Faculty getFacultyById(Long uId);
    boolean removeFacultyById(Long uId);
}
