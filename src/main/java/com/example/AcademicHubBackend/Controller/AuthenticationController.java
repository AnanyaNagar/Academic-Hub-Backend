package com.example.AcademicHubBackend.Controller;

import com.example.AcademicHubBackend.model.AuthenticationRequest;
import com.example.AcademicHubBackend.model.OrganizationUserModel;
import com.example.AcademicHubBackend.model.RegistrationRequest;
import com.example.AcademicHubBackend.repository.OrganizationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private OrganizationUserRepo organizationUserRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    private ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest){
        System.out.println("Reached registration");
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();
        String orgName = registrationRequest.getOrgName();

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
    private ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest){
        System.out.println("Reached login");
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();
        try {
            System.out.println("Reached try");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            System.out.println("logged in");
        }
        catch (Exception e){
            System.out.println(e.getCause());
            System.out.print(e.getMessage());
            return ResponseEntity.ok("Bad Credentials");
        }
        return ResponseEntity.ok("Organization was successfully authenticated");
    }
}
