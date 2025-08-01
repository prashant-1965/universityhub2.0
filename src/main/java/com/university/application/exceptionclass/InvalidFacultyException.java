package com.university.application.exceptionclass;

public class InvalidFacultyException extends RuntimeException{
    public InvalidFacultyException(String mess){
        super(mess);
    }
}
