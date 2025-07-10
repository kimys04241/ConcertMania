package com.company.assignment.common.hadnler;

import com.company.assignment.common.api.ApiStatus;
import com.company.assignment.common.domian.response.AssignmentResponse;
import com.company.assignment.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AssignmentExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(AssignmentResponse.error(ApiStatus.SYSTEM_EXCEPTION_CODE, ApiStatus.SYSTEM_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(AssignmentResponse.error(ApiStatus.REQUIRED_FIELD_EXCEPTION_CODE, ApiStatus.REQUIRED_FIELD_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<?> handleRequiredFieldException(RequiredFieldException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(AssignmentResponse.error(ex.getCode(), ex.getMessage()));
    }


    @ExceptionHandler(AlreadyExistUserException.class)
    public ResponseEntity<?> handleAlreadyBoundException(AlreadyExistUserException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(AssignmentResponse.error(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(LoginInfoNotMatchedException.class)
    public ResponseEntity<?> handleLoginInfoNotMatchedException(LoginInfoNotMatchedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(AssignmentResponse.error(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(AssignmentResponse.error(ex.getCode(), ex.getMessage()));
    }
}
