package com.example.AcademicHubBackend.Controller;
import com.example.AcademicHubBackend.model.StudentAuthenticationModel;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.Service.AdminStduentInfoService;

@RestController
@RequestMapping("/adm/v2")
public class AdminStudentInfoController {

    @Autowired
    private AdminStudentInfoRepo adminStudentInfoRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminStduentInfoService adminStduentInfoService;

    // add new student
    @PostMapping("/admin/studentinfo/addstudent")
    public ResponseEntity<?> addStduent(@RequestBody AdminStudentInfo studentInfo){
        adminStduentInfoService.addStudent(studentInfo);
        return new ResponseEntity<>(studentInfo,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable String studentId){
        adminStudentInfoRepo.deleteById(studentId);
        return "Student Information Deleted Successfully";
    }




}
