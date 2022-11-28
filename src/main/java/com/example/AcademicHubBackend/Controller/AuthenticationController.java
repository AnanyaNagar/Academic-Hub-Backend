package com.example.AcademicHubBackend.Controller;

import com.example.AcademicHubBackend.model.*;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import com.example.AcademicHubBackend.repository.OrganizationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthenticationController {


    @Autowired
    private OrganizationUserRepo organizationUserRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminStudentInfoRepo adminStudentInfoRepo;

    @PostMapping("/register")
    private ResponseEntity<String> register(@RequestBody OrgRegistrationModel orgRegistrationModel){
        String email = orgRegistrationModel.getEmail();
        String password = orgRegistrationModel.getPassword();
        String orgName = orgRegistrationModel.getOrgName();
        List<String> courses = orgRegistrationModel.getCourses();
        List<String> departments = orgRegistrationModel.getDepartments();

        OrganizationUserModel organizationUserModel =  new OrganizationUserModel();
        organizationUserModel.setEmail(email);
        organizationUserModel.setPassword(password);
        organizationUserModel.setOrgName(orgName);
        organizationUserModel.setCourses(courses);
        organizationUserModel.setDepartments(departments);
        try {
            organizationUserRepo.save(organizationUserModel);
        }
        catch (Exception e){
            return ResponseEntity.ok("Error during registration");
        }
        return ResponseEntity.ok(orgName + "Organization was successfully Registered");
    }


    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody OrgAuthenticationModel orgAuthenticationModel){
        String email = orgAuthenticationModel.getEmail();

        String password = orgAuthenticationModel.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }
        catch (Exception e){
            System.out.println(e.getCause());
            System.out.print(e.getMessage());
            return ResponseEntity.ok("Bad Credentials");
        }
        return ResponseEntity.ok("Organization was successfully authenticated");
    }


    @PostMapping("/student/login")
    private ResponseEntity<String> studentLogin(@RequestBody StudentAuthenticationModel studentAuthenticationModel){
        String enrollment = studentAuthenticationModel.getEnrollment();
        String password = studentAuthenticationModel.getPassword();
        try {
            if(adminStudentInfoRepo.existsById(enrollment) && adminStudentInfoRepo.findById(enrollment)
                    .get().getPassword().equals(password))
                System.out.println("Found!");
            else
                throw new UsernameNotFoundException("Either the enrollment or password is wrong!");
        }
        catch (Exception e){
            System.out.println(e.toString());
            System.out.print(e.getMessage());
            return ResponseEntity.ok("Bad Credentials");
        }
        return ResponseEntity.ok("Student successfully logged in");
    }

    @GetMapping("/admin/totalDepartments")
    public ResponseEntity<?> totalDepartments(@RequestBody String email){
        Integer totalDept = organizationUserRepo.findById(email).get().getDepartments().size();
        return new ResponseEntity<>(totalDept, HttpStatus.OK);
    }

    @GetMapping("/admin/totalCourses")
    public ResponseEntity<?> totalCourses(@RequestBody String email){
        Integer totalDept = organizationUserRepo.findById(email).get().getCourses().size();
        return new ResponseEntity<>(totalDept, HttpStatus.OK);
    }

    @GetMapping("/admin/studentCoursesInfo")
    public ResponseEntity<?> studentCoursesInfo(@RequestBody String email){
        List<String> courses = organizationUserRepo.findById(email).get().getCourses();
        ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();
        for(String course: courses){
            ArrayList<String> temp = new ArrayList<String>();
            Integer count = adminStudentInfoRepo.findBybranch(course).size();
            temp.add(course);
            temp.add(count.toString());
            response.add(temp);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
