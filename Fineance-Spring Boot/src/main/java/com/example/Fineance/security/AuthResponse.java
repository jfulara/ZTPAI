package com.example.Fineance.security;

public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter dla token
    public String getToken() {
        return token;
    }

    // Setter dla token (opcjonalny)
    public void setToken(String token) {
        this.token = token;
    }
}

