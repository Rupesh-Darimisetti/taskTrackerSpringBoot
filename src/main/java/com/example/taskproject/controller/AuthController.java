package com.example.taskproject.controller;

import com.example.taskproject.payload.LoginDTO;
import com.example.taskproject.payload.UserDTO;
import com.example.taskproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    // POST store the user data
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
       return new ResponseEntity<>( userService.createUser(userDTO), HttpStatus.CREATED);
    }
@PostMapping("/login")
    public ResponseEntity<String> loginUser(
            @RequestBody LoginDTO loginDTO
            ){
        Authentication authentication=
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDTO.getEmail(),
                                loginDTO.getPassword()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    return new ResponseEntity<>("User Logged In Successfully",HttpStatus.OK);
    }
}
