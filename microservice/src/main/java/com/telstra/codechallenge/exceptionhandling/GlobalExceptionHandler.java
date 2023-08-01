package com.telstra.codechallenge.exceptionhandling;


import com.telstra.codechallenge.constants.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.net.ConnectException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExceptionClass.class)
    public ResponseEntity<ErrorResponse> httpClientErrorException(ExceptionClass ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(ex.getStatusCode());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());

    }

    @ExceptionHandler(ConnectException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse connectException(ConnectException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(ErrorConstants.ERROR_CODE_2);
        errorResponse.setMessage(ErrorConstants.ERROR_RESPONSE_503);
        return errorResponse;

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> constraintViolationException(ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(ErrorConstants.ERROR_CODE_4);
        errorResponse.setMessage(ErrorConstants.CONSTRAINT_VIOLATION_EXCEPTION + " " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(ErrorConstants.ERROR_CODE_4);
        errorResponse.setMessage(ErrorConstants.ARGUMENT_TYPE_MISMATCH_EXCEPTION);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}
