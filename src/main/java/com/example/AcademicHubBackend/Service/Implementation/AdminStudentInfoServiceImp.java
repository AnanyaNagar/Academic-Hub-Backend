package com.example.AcademicHubBackend.Service.Implementation;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

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
        AdminStudentInfo studentInfo1=new AdminStudentInfo();
        studentInfo1.setEnrollment(studentInfo.getEnrollment());
        studentInfo1.setFirstName(studentInfo.getFirstName());
        studentInfo1.setLastName(studentInfo.getLastName());
        studentInfo1.setEmail(studentInfo.getEmail());
        studentInfo1.setYear(studentInfo.getYear());
        studentInfo1.setPassword(studentInfo.getPassword());
        emailSenderService.sendEmail(studentInfo.getEmail(),studentInfo.getPassword(),studentInfo.getFirstName(),"Student login details","www.");
        adminStudentInfoRepo.save(studentInfo1);
        return studentInfo1;


    }

    @Override
    public String deleteStudent(String studentId) {
        return null;
    }
}
