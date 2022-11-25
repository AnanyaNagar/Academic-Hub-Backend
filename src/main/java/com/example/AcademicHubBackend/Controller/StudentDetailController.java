package com.example.AcademicHubBackend.Controller;

import com.example.AcademicHubBackend.Service.Implementation.PdfService;
import com.example.AcademicHubBackend.Service.StudentDetailService;
import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.AcademicHubBackend.model.ProjectDescriptionModel;
import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/student")
public class StudentDetailController {

    // delete project
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

    @PutMapping("/editPassword/{enrollment}")
    public ResponseEntity<?> editPassword(@RequestBody String password, @PathVariable String enrollment){
        System.out.println(password);
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

    @PostMapping("/firstProjects/{enrollment}")
    public ResponseEntity<?> firstProjects(@RequestBody ProjectDescriptionModel projectDescriptionModel, @PathVariable String enrollment){
//        AdminStudentInfo adminStudentInfo = adminStudentInfoRepo.findById(enrollment).get();
        studentDetailService.addFirstProject(projectDescriptionModel, enrollment);
        return ResponseEntity.ok("Project added");
    }

    //add extra projects
    @PutMapping("/addProjects/{enrollment}")
    public ResponseEntity<?> addProjects(@RequestBody ProjectDescriptionModel projectDescriptionModel, @PathVariable String enrollment){
//        AdminStudentInfo adminStudentInfo = adminStudentInfoRepo.findById(enrollment).get();
        studentDetailService.addProject(projectDescriptionModel, enrollment);
        return ResponseEntity.ok("Project added");
    }

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
