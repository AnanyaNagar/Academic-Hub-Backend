package com.example.AcademicHubBackend.Controller;

import com.example.AcademicHubBackend.Service.AdminStduentInfoService;
import com.example.AcademicHubBackend.Service.StudentDetailService;
import com.example.AcademicHubBackend.model.AdminStudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentDetailController {
    // add skills
    // delete skills
    // add project
    // delete project
    // change password
    @Autowired
    StudentDetailService studentDetailService;

    @PostMapping("/editPassword/{studentId}")
    public ResponseEntity<?> editPassword(@RequestBody String password, @RequestBody String studentId){
        studentDetailService.changePassword(studentId, password);
        return ResponseEntity.ok("Password changed");
    }
}
