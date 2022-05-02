package com.example.demo.exceptions;

public class ApiRequestExeptions extends RuntimeException{
    public ApiRequestExeptions(String message) {
        super(message);
    }

    public ApiRequestExeptions(String message, Throwable cause) {
        super(message, cause);
    }
}
