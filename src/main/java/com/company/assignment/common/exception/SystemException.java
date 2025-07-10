package com.company.assignment.common.exception;

public class SystemException extends AssignmentException {

    public SystemException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
