package com.example.AcademicHubBackend.Controller;

import com.example.AcademicHubBackend.Service.AdminStduentInfoService;
import com.example.AcademicHubBackend.Service.Implementation.PdfService;
import com.example.AcademicHubBackend.Service.StudentDetailService;
import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.AcademicHubBackend.model.ProjectDescriptionModel;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentDetailController {

    // add project
    // delete project
    // change password
    @Autowired
    StudentDetailService studentDetailService;

    @Autowired
    AdminStudentInfoRepo adminStudentInfoRepo;

    @Autowired
    PdfService pdfService;

    @GetMapping("{enrollment}")
    public ResponseEntity<AdminStudentInfo> getProfile(@PathVariable String enrollment){
        return ResponseEntity.ok(adminStudentInfoRepo.findById(enrollment).get());
    }

    @PostMapping("/editPassword/{enrollment}")
    public ResponseEntity<?> editPassword(@RequestBody String password, @PathVariable String enrollment){
        studentDetailService.changePassword(enrollment, password);
        return ResponseEntity.ok("Password changed");
    }

    @PostMapping("/addSkills/{enrollment}")
    public ResponseEntity<?> addSkills(@RequestBody AdminStudentInfo skills, @PathVariable String enrollment){
        AdminStudentInfo adminStudentInfo = adminStudentInfoRepo.findById(enrollment).get();
        adminStudentInfo.setSkills(skills.getSkills());
        adminStudentInfoRepo.save(adminStudentInfo);
        return ResponseEntity.ok("Skills added");
    }

    @PutMapping("/editSkills/{enrollment}")
    public ResponseEntity<?> editSkills(@RequestBody AdminStudentInfo skills, @PathVariable String enrollment){
        AdminStudentInfo adminStudentInfo = adminStudentInfoRepo.findById(enrollment).get();
//        String newSkills = adminStudentInfo.getSkills() + ", " + skills;
        adminStudentInfo.setSkills(skills.getSkills());
        adminStudentInfoRepo.save(adminStudentInfo);
        return ResponseEntity.ok("Skills updated");
    }

    @PostMapping("/addProjects/{enrollment}")
    public ResponseEntity<?> addProjects(@RequestBody ProjectDescriptionModel projectDescriptionModel, @PathVariable String enrollment){
//        AdminStudentInfo adminStudentInfo = adminStudentInfoRepo.findById(enrollment).get();
        studentDetailService.addFirstProject(projectDescriptionModel, enrollment);
        return ResponseEntity.ok("Project added");
    }

    //add extra projects

    //edit projects


    @PostMapping("/generatePdf/{enrollment}")
    public ResponseEntity<InputStreamResource> generatePdf(@PathVariable String enrollment){
        ByteArrayInputStream pdf = pdfService.createPdf(enrollment);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline;file=Resume.pdf");
        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

}
