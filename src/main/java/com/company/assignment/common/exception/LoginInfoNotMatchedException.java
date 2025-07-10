package com.company.assignment.common.exception;

public class LoginInfoNotMatchedException extends AssignmentException {

    public LoginInfoNotMatchedException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
