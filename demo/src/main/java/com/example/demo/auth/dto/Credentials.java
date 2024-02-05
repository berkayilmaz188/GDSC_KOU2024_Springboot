package com.example.demo.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Credentials {
    private String email;
    private String password;

    // Constructor, Getters ve Setters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
