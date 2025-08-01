package com.university.application.exceptionclass;

public class InvalidStudentException extends RuntimeException{
    public InvalidStudentException(String mess){
        super(mess);
    }
}