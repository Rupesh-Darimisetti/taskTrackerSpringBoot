package com.example.taskproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class APIException extends RuntimeException{
    private final String message;

    public APIException(String  message){
        super(message);
        this.message = message;
    }
}
