package com.example.AcademicHubBackend.Controller;

import com.example.AcademicHubBackend.Service.AdminTeacherInfoService;
import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.model.AdminTeacherInfo;
import com.example.AcademicHubBackend.repository.AdminTeacherInfoRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.Service.AdminStduentInfoService;

import java.text.ParseException;

@RestController
@RequestMapping("/adm/v3")
public class AdminTeacherInfoController {

    @Autowired
    AdminTeacherInfoRepo adminTeacherInfoRepo;

    @Autowired
    AdminTeacherInfoService adminTeacherInfoService;


    @PostMapping("/admin/teacherInfo/addTeacher")
    public ResponseEntity<?> addTeacher(@RequestBody AdminTeacherInfo teacherInfo){
        adminTeacherInfoService.addTeacher(teacherInfo);
        return new ResponseEntity<>(teacherInfo, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete/{teacherId}")
    public String deleteStudent(@PathVariable String teacherId){
        adminTeacherInfoService.deleteTeacher(teacherId);
        return "Teacher Information Deleted Successfully";
    }

    @GetMapping("/admin/teacherInfo/allTeachers")
    public ResponseEntity<?> allTeachers(){
        long count = adminTeacherInfoRepo.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
