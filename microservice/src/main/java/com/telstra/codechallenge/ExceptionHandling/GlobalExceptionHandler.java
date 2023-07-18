package com.telstra.codechallenge.ExceptionHandling;


import com.telstra.codechallenge.Constants.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        errorResponse.setStatusCode(Constants.ERROR_CODE2);
        errorResponse.setMessage(Constants.Error_RESPONSE503);
        return errorResponse;

    }
}
