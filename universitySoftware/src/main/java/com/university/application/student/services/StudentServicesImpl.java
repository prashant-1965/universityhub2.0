package com.university.application.student.services;

import com.university.application.student.Repository.StudentRepository;
import com.university.application.student.entity.Student;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServicesImpl implements StudentServices{
    @Autowired
    StudentRepository studentRepository;
    public boolean addStudent(Student student)
    {
        Student student1 =  studentRepository.save(student);
        return student1.getStdId()!=null;
    }
    public List<Student> allStudent()
    {
        return studentRepository.findAll();
    }
    public Student studentById(Long id)
    {
//        return studentRepository.getReferenceById(id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }
    @Transactional
    public boolean removeStudent(Long id)
    {
        if(studentRepository.existsById(id))
        {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
