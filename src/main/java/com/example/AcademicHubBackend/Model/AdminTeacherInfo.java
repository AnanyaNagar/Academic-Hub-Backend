package com.example.AcademicHubBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "AdminTeacher")
public class AdminTeacherInfo {
    @Id
    private String teacherId;
    private String firstName ;
    private String lastName ;

    @Indexed(unique = true)
    private String email;

    private String department;
    private String subject;
}
