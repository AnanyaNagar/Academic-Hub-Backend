package com.example.AcademicHubBackend.Controller;

import java.util.List;

import com.example.AcademicHubBackend.Repository.AdminStudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AcademicHubBackend.Model.AdminStudentInfo;
import com.example.AcademicHubBackend.Service.AdminStduentInfoService;

@RestController
@RequestMapping("/adm/v2")
public class AdminStudentInfoController {

    @Autowired
    private AdminStudentInfoRepo adminStudentInfoRepo;

    @Autowired
    private AdminStduentInfoService adminStduentInfoService;

    // add new student
    @PostMapping("/admin/studentinfo/addstudent")
    public ResponseEntity<?> addStduent(@RequestBody AdminStudentInfo studentInfo){
        adminStduentInfoService.addStudent(studentInfo);
        return new ResponseEntity<>(studentInfo,HttpStatus.CREATED);
    }






}
