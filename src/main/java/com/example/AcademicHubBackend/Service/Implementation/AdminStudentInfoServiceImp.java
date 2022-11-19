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
public class AdminStudentInfoServiceImp implements AdminStduentInfoService, UserDetailsService {

    @Autowired
    private AdminStudentInfoRepo adminStudentInfoRepo;



    @Override
    public AdminStudentInfo addStudent(AdminStudentInfo studentInfo) {
        adminStudentInfoRepo.save(studentInfo);
        return studentInfo;
    }

    @Override
    public UserDetails loadUserByUsername(String enrollment) throws UsernameNotFoundException {
        System.out.println("Finally chal gya");
        AdminStudentInfo data = adminStudentInfoRepo.findByEnrollment(enrollment);
        if(data == null)
            throw new UsernameNotFoundException(enrollment + " not found");

        String password = data.getPassword();

        return new User(enrollment, password, new ArrayList<>());
    }
}
