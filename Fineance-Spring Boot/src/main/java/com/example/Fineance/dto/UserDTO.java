package com.example.Fineance.dto;

public class UserDTO {
    private Long id_user;
    private String name;
    private String surname;
    private String email;
    private String role;

    public UserDTO(Long id_user, String name, String surname, String email, String role) {
        this.id_user = id_user;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
