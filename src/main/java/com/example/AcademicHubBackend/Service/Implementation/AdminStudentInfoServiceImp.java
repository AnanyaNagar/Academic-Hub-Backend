package com.example.AcademicHubBackend.Service.Implementation;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AdminStudentInfo addStudent(AdminStudentInfo studentInfo) throws ParseException {

        studentInfo.setDateOfAdmission(java.time.LocalDate.now().toString());
        emailSenderService.sendEmail(studentInfo.getEmail(),studentInfo.getPassword(),studentInfo.getFirstName(),"Student login details");
        adminStudentInfoRepo.save(studentInfo);
        return studentInfo;
    }

    @Override
    public String deleteStudent(String studentId) {
        return null;
    }
}
