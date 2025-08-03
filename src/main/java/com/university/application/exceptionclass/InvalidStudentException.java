package com.university.application.exceptionclass;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidStudentException extends RuntimeException{
    private final HttpStatus status;
    public InvalidStudentException(String mess, HttpStatus status){
        super(mess);
        this.status = status;
    }
}