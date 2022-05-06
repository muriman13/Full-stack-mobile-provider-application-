package com.example.demo.exceptions;

public class NoEntityFound extends RuntimeException{
    public NoEntityFound(String message) {
        super(message);
    }

    public NoEntityFound(String message, Throwable cause) {
        super(message, cause);
    }
}
