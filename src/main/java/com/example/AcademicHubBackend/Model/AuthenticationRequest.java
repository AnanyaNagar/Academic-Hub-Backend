package com.example.AcademicHubBackend.model;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;

}
