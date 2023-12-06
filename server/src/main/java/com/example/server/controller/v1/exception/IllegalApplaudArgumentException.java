package com.example.server.controller.v1.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class IllegalApplaudArgumentException extends ResponseStatusException {
    public IllegalApplaudArgumentException() {
        super(BAD_REQUEST);
    }
}
