package com.example.AcademicHubBackend.Service;

import com.example.AcademicHubBackend.model.ProjectDescriptionModel;

public interface StudentDetailService {

    public String changePassword(String enrollment, String password);

    public void addFirstProject(ProjectDescriptionModel projectDescriptionModel, String enrollment);
}
