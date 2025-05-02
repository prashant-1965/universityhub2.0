package com.university.application.faculty.service;

import com.university.application.faculty.entity.Faculty;
import com.university.application.faculty.repository.FacultyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService{

    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public boolean addFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        return true;
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getFacultyById(Long uId) {
        return facultyRepository.findById(uId)
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + uId));
    }

    @Transactional
    @Override
    public boolean removeFacultyById(Long uId)
    {
        if (facultyRepository.existsById(uId))
        {
            facultyRepository.deleteById(uId);
            return true;
        }
        return false;
    }
}
