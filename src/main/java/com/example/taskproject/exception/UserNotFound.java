package com.example.taskproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException{
    private final String message;
    public UserNotFound(String message){
        super(message);
        this.message = message;
    }
}
