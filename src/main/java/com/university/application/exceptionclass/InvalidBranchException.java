package com.university.application.exceptionclass;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidBranchException extends RuntimeException {
    private final HttpStatus status;
    public InvalidBranchException(String mes,HttpStatus status){
        super(mes);
        this.status = status;
    }
}
