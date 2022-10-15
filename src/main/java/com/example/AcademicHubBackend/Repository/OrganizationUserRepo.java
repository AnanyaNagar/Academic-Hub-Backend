package com.example.AcademicHubBackend.repository;

import com.example.AcademicHubBackend.model.OrganizationUserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationUserRepo extends MongoRepository<OrganizationUserModel, String> {

    OrganizationUserModel findByEmail(String email);
}
