package com.company.assignment.common.exception;

public class AlreadyExistUserException extends AssignmentException {

    public AlreadyExistUserException(String code, String message) {

        this.code = code;
        this.message = message;
    }
}
