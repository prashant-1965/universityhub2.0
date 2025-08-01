package com.university.application.exceptionclass;

public class InvalidBranchException extends RuntimeException {
    public InvalidBranchException(String mes){
        super(mes);
    }
}
