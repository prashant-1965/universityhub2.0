package com.university.application.exceptionclass;

public class InvalidSectionException extends RuntimeException{
    public InvalidSectionException(String mes){
        super(mes);
    }
}
