package com.example.AcademicHubBackend.Service;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.model.AdminTeacherInfo;

import java.text.ParseException;

public interface AdminTeacherInfoService {

    public AdminTeacherInfo addTeacher(AdminTeacherInfo teacherInfo);
    public String deleteTeacher(String teacherId);
}
