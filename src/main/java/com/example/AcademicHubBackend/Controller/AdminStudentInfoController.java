package com.example.AcademicHubBackend.Controller;

import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.Service.AdminStduentInfoService;
import java.text.ParseException;
import java.util.HashMap;

@RestController
@RequestMapping("/adm/v2")
public class AdminStudentInfoController {

    @Autowired
    private AdminStudentInfoRepo adminStudentInfoRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminStduentInfoService adminStduentInfoService;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/admin/studentInfo/studentCount")
    public ResponseEntity<?> studentCount(){
        long count = adminStudentInfoRepo.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


    @PostMapping("/admin/studentinfo/addstudent")
    public ResponseEntity<?> addStduent(@RequestBody AdminStudentInfo studentInfo) throws ParseException {
        adminStduentInfoService.addStudent(studentInfo);
        return new ResponseEntity<>(studentInfo,HttpStatus.CREATED);
    }

    @DeleteMapping("admin/delete/{enrollment}")
    public String deleteStudent(@PathVariable String enrollment){
        adminStudentInfoRepo.deleteById(enrollment);
        return "Student Information Deleted Successfully";
    }

    @PutMapping("/admin/studentinfo/editStudent/{enrollment}")
    public String editStudent(@RequestBody AdminStudentInfo adminStudentInfo, @PathVariable String enrollment){
        AdminStudentInfo temp = adminStudentInfoRepo.findById(enrollment).get();
        temp.setEnrollment(adminStudentInfo.getEnrollment());
//        temp.setPassword(adminStudentInfo.getPassword());
        temp.setDepartment(adminStudentInfo.getDepartment());
        temp.setEmail(adminStudentInfo.getEmail());
        temp.setBranch(adminStudentInfo.getBranch());
        temp.setYear(adminStudentInfo.getYear());
        temp.setFirstName(adminStudentInfo.getFirstName());
        temp.setLastName(adminStudentInfo.getLastName());
        adminStudentInfoRepo.deleteById(enrollment);
        adminStudentInfoRepo.save(temp);
        return "Successfully changed";
    }


}
