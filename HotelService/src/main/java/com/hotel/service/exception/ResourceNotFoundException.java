package com.hotel.service.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
