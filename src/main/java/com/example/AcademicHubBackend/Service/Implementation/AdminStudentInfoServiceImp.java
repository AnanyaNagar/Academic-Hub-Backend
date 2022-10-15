package com.example.AcademicHubBackend.Service.Implementation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import com.example.AcademicHubBackend.Service.AdminStduentInfoService;
@Service
public class AdminStudentInfoServiceImp implements AdminStduentInfoService{

    @Autowired
    private AdminStudentInfoRepo adminStudentInfoRepo;



    @Override
    public AdminStudentInfo addStudent(AdminStudentInfo studentInfo) {
        AdminStudentInfo studentInfo1=new AdminStudentInfo();
        studentInfo1.setFirstName(studentInfo.getFirstName());
        studentInfo1.setLastName(studentInfo.getLastName());
        studentInfo1.setEmail(studentInfo.getEmail());
        studentInfo1.setYear(studentInfo.getYear());
        adminStudentInfoRepo.save(studentInfo1);
        return studentInfo1;
    }
}
