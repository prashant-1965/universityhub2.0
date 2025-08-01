package com.university.application.services;

import com.university.application.entity.Faculty;

import java.util.List;

public interface FacultyService {
    String addFaculty(Faculty faculty);
    List<Faculty> getAllFaculty();
    String getFacultyById(Long uId);
    String removeFacultyById(Long uId);
}
