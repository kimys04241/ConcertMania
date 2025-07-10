package com.company.assignment.common.exception;

import lombok.Getter;

@Getter
public class AssignmentException extends RuntimeException {

    protected String code;
    protected String message;
}
