package com.example.AcademicHubBackend.Service;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;


public interface AdminStduentInfoService {
    public AdminStudentInfo addStudent(AdminStudentInfo studentInfo) throws ParseException;
    public String deleteStudent(String studentId);
}
