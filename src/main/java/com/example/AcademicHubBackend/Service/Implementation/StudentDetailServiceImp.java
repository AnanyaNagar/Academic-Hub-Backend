package com.example.AcademicHubBackend.Service.Implementation;

import com.example.AcademicHubBackend.Service.StudentDetailService;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailServiceImp implements StudentDetailService {

    @Autowired
    AdminStudentInfoRepo adminStudentInfoRepo;

    @Override
    public String changePassword(String enrollment, String password) {
        try {
            adminStudentInfoRepo.findById(enrollment).get().setPassword(password);
        }
        catch (Exception e){
            return e.getMessage();
        }
        return "Password changed";
    }
}
