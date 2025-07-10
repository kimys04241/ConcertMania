package com.company.assignment.common.exception;

public class DataNotFoundException extends AssignmentException {

    public DataNotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
