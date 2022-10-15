package com.example.AcademicHubBackend.model;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String email;
    private String orgName;
    private String password;
}
