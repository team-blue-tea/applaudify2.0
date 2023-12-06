package com.example.server.controller.v1.exception;

import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class MemberNotFoundException extends ResponseStatusException {
    public MemberNotFoundException(UUID memberId) {
        super(NOT_FOUND, "Member with ID %s not found.".formatted(memberId));
    }
}
