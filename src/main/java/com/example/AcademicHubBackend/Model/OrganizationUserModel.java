package com.example.AcademicHubBackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "OrganizationUsers")
@Data
public class OrganizationUserModel {
    @Id
    private String email;

    private String orgName;

    private String password;

    private List<String> courses;

    private List<String> departments;
}
