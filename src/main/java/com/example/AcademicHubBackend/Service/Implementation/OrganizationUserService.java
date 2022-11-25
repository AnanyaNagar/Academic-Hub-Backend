package com.example.AcademicHubBackend.Service.Implementation;

import com.example.AcademicHubBackend.model.OrganizationUserModel;
import com.example.AcademicHubBackend.repository.OrganizationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class OrganizationUserService implements UserDetailsService {

    @Autowired
    private OrganizationUserRepo organizationUserRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        OrganizationUserModel retrieved_data = organizationUserRepo.findByEmail(email);
        if (retrieved_data == null) return null;

        String password = retrieved_data.getPassword();

        return new User(email, password, new ArrayList<>());
    }
}
