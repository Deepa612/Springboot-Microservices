package com.telstra.codechallenge.ExceptionHandling;

import lombok.Data;

@Data
public class ErrorResponse {
    private String statusCode;
    private String message;

}
