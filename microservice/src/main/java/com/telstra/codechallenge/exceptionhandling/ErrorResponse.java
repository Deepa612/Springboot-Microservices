package com.telstra.codechallenge.exceptionhandling;

import lombok.Data;

@Data
public class ErrorResponse {
    private String statusCode;
    private String message;

}
