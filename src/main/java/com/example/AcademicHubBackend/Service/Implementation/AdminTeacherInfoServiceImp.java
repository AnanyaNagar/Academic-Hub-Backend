package com.example.AcademicHubBackend.Service.Implementation;

import com.example.AcademicHubBackend.Service.AdminTeacherInfoService;
import com.example.AcademicHubBackend.model.AdminTeacherInfo;
import com.example.AcademicHubBackend.repository.AdminTeacherInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminTeacherInfoServiceImp implements AdminTeacherInfoService {

    @Autowired
    AdminTeacherInfoRepo adminTeacherInfoRepo;

    @Override
    public AdminTeacherInfo addTeacher(AdminTeacherInfo teacherInfo) {
        adminTeacherInfoRepo.save(teacherInfo);
        return teacherInfo;
    }

    @Override
    public String deleteTeacher(String teacherId) {
        adminTeacherInfoRepo.deleteById(teacherId);
        return "Successfully deleted";
    }
}
