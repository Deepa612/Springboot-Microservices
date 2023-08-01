package com.telstra.codechallenge.ExceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionClass extends RuntimeException {
    private String statusCode;
    private String message;
    private HttpStatus httpStatus;
}
