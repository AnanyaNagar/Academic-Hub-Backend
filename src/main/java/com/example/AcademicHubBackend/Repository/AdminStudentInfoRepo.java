package com.example.AcademicHubBackend.repository;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminStudentInfoRepo extends MongoRepository<AdminStudentInfo,String>{
    List<AdminStudentInfo> findBybranch(String branch);
}
