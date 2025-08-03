package com.university.application.exceptionclass;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidFacultyException extends RuntimeException{
    private final HttpStatus status;
    public InvalidFacultyException(String mess, HttpStatus status){
        super(mess);
        this.status = status;
    }
}
