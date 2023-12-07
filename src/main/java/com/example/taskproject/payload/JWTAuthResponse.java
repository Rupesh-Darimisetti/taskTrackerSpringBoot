package com.example.taskproject.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JWTAuthResponse {
    private String token;
    private final String tokenType="Bearer";

    public JWTAuthResponse(String token){
        this.token = token;
    }
}
