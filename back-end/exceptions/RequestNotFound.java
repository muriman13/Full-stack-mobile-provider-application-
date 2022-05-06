package com.example.demo.exceptions;

public class RequestNotFound extends RuntimeException{
    public RequestNotFound(String message) {
        super(message);
    }

    public RequestNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
