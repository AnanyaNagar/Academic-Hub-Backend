package com.example.AcademicHubBackend.Service;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import org.springframework.stereotype.Service;


public interface AdminStduentInfoService {
    public AdminStudentInfo addStudent(AdminStudentInfo studentInfo);
    public String deleteStudent(String studentId);
}
