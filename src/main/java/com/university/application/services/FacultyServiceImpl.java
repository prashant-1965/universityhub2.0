package com.university.application.services;

import com.university.application.entity.Faculty;
import com.university.application.exceptionclass.InvalidBranchException;
import com.university.application.exceptionclass.InvalidFacultyException;
import com.university.application.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public String addFaculty(Faculty faculty) throws InvalidFacultyException {
        if(faculty.getLocation()== null || faculty.getFirstName()==null || faculty.getLastName()==null){
            throw  new InvalidFacultyException("Faculty values should not be null");
        }
        facultyRepository.save(faculty);
        return "Success";
    }

    @Override
    public List<Faculty> getAllFaculty()  throws InvalidFacultyException{
        List<Faculty> val = facultyRepository.findAll();
        if(val.isEmpty()) throw new InvalidFacultyException("No faculty found!");
        return val;
    }

    @Override
    public String getFacultyById(Long uId)  throws InvalidBranchException {
        if (!facultyRepository.existsById(uId)) {
            throw new InvalidBranchException("Faculty not found with id: " + uId);
        }
        return facultyRepository.findById(uId).toString();
    }

    @Transactional
    @Override
    public String removeFacultyById(Long uId) throws InvalidFacultyException
    {
        if (!facultyRepository.existsById(uId))
        {
            throw new InvalidFacultyException("Faculty not found with id: " + uId);
        }
        facultyRepository.deleteById(uId);
        return "Faculty "+ uId+" removed Successfully";
    }
}
