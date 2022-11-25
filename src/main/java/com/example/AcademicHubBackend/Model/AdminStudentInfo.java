package com.example.AcademicHubBackend.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "AdminStudent")
public class AdminStudentInfo {
    @Id
    private String enrollment;
    private String firstName ;
    private String lastName ;
    private Integer year;

    @Indexed(unique = true)
    private String email;

    private String password;
    private String department;
    private String branch;

    private String skills;
    private List<ProjectDescriptionModel> projects;
    private String course;
    private String dateOfAdmission;
    private String phoneNumber;
    private String gender;

}
