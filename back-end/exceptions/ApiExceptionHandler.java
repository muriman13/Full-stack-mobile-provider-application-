package com.example.demo.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestExeptions.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestExeptions e){
       ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
       return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {RequestNotFound.class})
    public ResponseEntity<Object> handleApiRequestException(RequestNotFound e){
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {NoEntityFound.class})
    public ResponseEntity<Object> handleApiRequestException(NoEntityFound e){
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
