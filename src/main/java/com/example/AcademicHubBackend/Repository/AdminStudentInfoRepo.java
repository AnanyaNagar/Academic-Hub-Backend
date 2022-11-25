package com.example.AcademicHubBackend.repository;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminStudentInfoRepo extends MongoRepository<AdminStudentInfo,String>{
}
