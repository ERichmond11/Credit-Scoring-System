package com.emmanuel.creditrisk.backend.dto;

public class AuthRequest {

    private String name;
    private String email;
    private String password;

    public AuthRequest() {}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
