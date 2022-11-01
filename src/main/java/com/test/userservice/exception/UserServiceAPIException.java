package com.test.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserServiceAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public UserServiceAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserServiceAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
