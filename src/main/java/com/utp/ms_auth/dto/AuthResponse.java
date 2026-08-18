package com.utp.ms_auth.dto;

import lombok.Getter;

@Getter
public class AuthResponse {
    private final String token;
    private final String type = "Bearer";
    private final String username;
    private final String email;

    public AuthResponse(String token, String username, String email) {
        this.token = token;
        this.username = username;
        this.email = email;
    }
}