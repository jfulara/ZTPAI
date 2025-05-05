package com.example.Fineance.security;

public class AuthRequest {
    private String email;
    private String password;

    // Getter dla email
    public String getEmail() {
        return email;
    }

    // Setter dla email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter dla password
    public String getPassword() {
        return password;
    }

    // Setter dla password
    public void setPassword(String password) {
        this.password = password;
    }
}
