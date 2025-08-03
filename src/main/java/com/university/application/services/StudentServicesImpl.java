package com.university.application.services;

import com.university.application.exceptionclass.InvalidStudentException;
import com.university.application.repository.StudentRepository;
import com.university.application.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServicesImpl implements StudentServices {
    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student) throws InvalidStudentException
    {
        if(student==null || student.getFirstName()==null || student.getEmail()==null || student.getLocation()==null){
            throw new InvalidStudentException("section fields can not be null",HttpStatus.BAD_REQUEST);
        }
        studentRepository.save(student);
        return "Section added Successfully";
    }
    public List<Student> allStudent()
    {
        List<Student> studentList = studentRepository.findAll();
        if(studentList.isEmpty()){
            throw new InvalidStudentException("No student record found",HttpStatus.NOT_FOUND);
        }
        return studentList;
    }
    public Optional<Student> studentById(Long id)
    {
        if(id==null){
            throw new InvalidStudentException("Please provide valid id",HttpStatus.BAD_REQUEST);
        }
        if(!studentRepository.existsById(id)){
            throw new InvalidStudentException("No student found with id: "+id,HttpStatus.NOT_FOUND);
        }
        return studentRepository.findById(id);
    }
    @Transactional
    public String removeStudent(Long id)
    {
        if(id==null){
            throw new InvalidStudentException("Please provide valid id",HttpStatus.BAD_REQUEST);
        }
        if(!studentRepository.existsById(id)){
            throw new InvalidStudentException("No student found with id: "+id,HttpStatus.NOT_FOUND);
        }
        studentRepository.deleteById(id);
        return "Student removed Successfully";
    }
}
