package com.example.taskproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TaskNotFound extends RuntimeException{
    private final String message;
    public  TaskNotFound(String message){
        super(message);
        this.message = message;
    }
}
