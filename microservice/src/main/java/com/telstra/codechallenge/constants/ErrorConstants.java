package com.telstra.codechallenge.constants;

public final class ErrorConstants {
    private ErrorConstants(){}

    public static final String ERROR_CODE_1 = "No content";
    public static final String ERROR_RESPONSE_404 = "No data found or invalid url";
    public static final String ERROR_CODE_2 = "unavailable";
    public static final String ERROR_RESPONSE_503 = "Server is down";
    public static final String ERROR_CODE_3 = "missing/invalid";
    public static final String ERROR_CODE_4 = "Bad Request";
    public static final String ERROR_RESPONSE_422 = "Please provide an integer value for the field q";
    public static final String NO_ACCOUNT_FOUND_204 = "No ACCOUNT found with 0 followers";
    public static final String CONSTRAINT_VIOLATION_EXCEPTION = "Input parameter is not valid due to validation:";
    public static final String ARGUMENT_TYPE_MISMATCH_EXCEPTION = "Value must be an integer";


}
