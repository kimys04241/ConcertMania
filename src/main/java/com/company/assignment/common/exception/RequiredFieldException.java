package com.company.assignment.common.exception;

public class RequiredFieldException extends AssignmentException {

    public RequiredFieldException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
