package com.university.application.globalexceptionhandler;

import com.university.application.exceptionclass.InvalidBranchException;
import com.university.application.exceptionclass.InvalidFacultyException;
import com.university.application.exceptionclass.InvalidSectionException;
import com.university.application.exceptionclass.InvalidStudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBranchException.class)
    public ResponseEntity<?> handelInvalidBranch(InvalidBranchException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(InvalidSectionException.class)
    public ResponseEntity<?> handelInvalidSection(InvalidSectionException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(InvalidFacultyException.class)
    public ResponseEntity<?> handelInvalidFaculty(InvalidFacultyException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(InvalidStudentException.class)
    public ResponseEntity<?> handelInvalidStudent(InvalidStudentException e){
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
