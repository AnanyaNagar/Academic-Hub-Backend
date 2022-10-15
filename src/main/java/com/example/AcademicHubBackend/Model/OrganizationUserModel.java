package com.example.AcademicHubBackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OrganizationUsers")
@Data
public class OrganizationUserModel {
    @Id
    private String email;

    private String orgName;

    private String password;
}
