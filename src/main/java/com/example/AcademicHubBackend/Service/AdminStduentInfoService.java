package com.example.AcademicHubBackend.Service;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public interface AdminStduentInfoService {
    public AdminStudentInfo addStudent(AdminStudentInfo studentInfo);

}
