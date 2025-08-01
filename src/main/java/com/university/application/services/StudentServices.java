package com.university.application.services;

import com.university.application.entity.Student;
import java.util.List;
import java.util.Optional;

public interface StudentServices {

    String addStudent(Student student);
    List<Student> allStudent();
    Optional<Student> studentById(Long id);
    String removeStudent(Long id);
}
