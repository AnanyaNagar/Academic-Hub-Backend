package com.example.AcademicHubBackend.Service.Implementation;

import com.example.AcademicHubBackend.Service.StudentDetailService;
import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.model.ProjectDescriptionModel;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void addFirstProject(ProjectDescriptionModel projectDescriptionModel, String enrollment) {
        AdminStudentInfo adminStudentInfo = adminStudentInfoRepo.findById(enrollment).get();
//        adminStudentInfo.getProjects().
        List<ProjectDescriptionModel> projects = new ArrayList<>();
        projects.add(projectDescriptionModel);
        adminStudentInfo.setProjects(projects);
        adminStudentInfoRepo.save(adminStudentInfo);
    }
}
