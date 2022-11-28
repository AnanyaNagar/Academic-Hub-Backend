package com.example.AcademicHubBackend.model;

import lombok.Data;

import java.util.List;

@Data
public class OrgRegistrationModel {

    private String email;
    private String orgName;
    private String password;
    private List<String> courses;
    private List<String> departments;
}
