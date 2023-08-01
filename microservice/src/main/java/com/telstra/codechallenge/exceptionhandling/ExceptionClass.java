package com.telstra.codechallenge.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionClass extends RuntimeException {
    private final String statusCode;
    private final String message;
    private final HttpStatus httpStatus;
}
