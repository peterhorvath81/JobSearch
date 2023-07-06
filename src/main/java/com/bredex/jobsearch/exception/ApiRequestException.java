package com.bredex.jobsearch.exception;

/**
 * Custom exception class for validation
 */
public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }
}
