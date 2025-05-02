package com.university.application.student.services;

import com.university.application.student.entity.Student;
import java.util.List;

public interface StudentServices {

    boolean addStudent(Student student);
    List<Student> allStudent();
    Student studentById(Long id);
    boolean removeStudent(Long id);
}
