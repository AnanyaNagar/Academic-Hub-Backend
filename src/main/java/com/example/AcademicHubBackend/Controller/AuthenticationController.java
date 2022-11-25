package com.example.AcademicHubBackend.Controller;

import com.example.AcademicHubBackend.model.*;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import com.example.AcademicHubBackend.repository.OrganizationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

        OrganizationUserModel organizationUserModel =  new OrganizationUserModel();
        organizationUserModel.setEmail(email);
        organizationUserModel.setPassword(password);
        organizationUserModel.setOrgName(orgName);
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

}
