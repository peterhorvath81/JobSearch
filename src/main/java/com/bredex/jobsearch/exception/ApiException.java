package com.bredex.jobsearch.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * Class for handling validation messages
 */
@AllArgsConstructor
@Getter
public class ApiException {

    private final String message;

    private final HttpStatus httpStatus;

    private final ZonedDateTime timestamp;
}
