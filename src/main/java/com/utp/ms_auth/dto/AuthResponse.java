package com.utp.ms_auth.dto;

import lombok.Getter;
import java.util.Set;

@Getter
public class AuthResponse {
    private final String token;
    private final String type = "Bearer";
    private final String username;
    private final String email;
    private final Set<String> roles;

    public AuthResponse(String token, String username, String email, Set<String> roles) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}