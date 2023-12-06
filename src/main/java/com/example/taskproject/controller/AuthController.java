package com.example.taskproject.controller;

import com.example.taskproject.payload.UserDTO;
import com.example.taskproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    // POST store the user data
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
       return new ResponseEntity<>( userService.createUser(userDTO), HttpStatus.CREATED);
    }
}
