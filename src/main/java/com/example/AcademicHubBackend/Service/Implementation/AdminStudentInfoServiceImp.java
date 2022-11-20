package com.example.AcademicHubBackend.Service.Implementation;
import java.util.ArrayList;
import java.util.Optional;

//import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import com.example.AcademicHubBackend.Service.AdminStduentInfoService;
@Service
public class AdminStudentInfoServiceImp implements AdminStduentInfoService {

    @Autowired
    private AdminStudentInfoRepo adminStudentInfoRepo;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public AdminStudentInfo addStudent(AdminStudentInfo studentInfo) {
        adminStudentInfoRepo.save(studentInfo);
        return studentInfo;
    }

    @Override
    public String deleteStudent(String studentId) {

        return null;
    }
}
