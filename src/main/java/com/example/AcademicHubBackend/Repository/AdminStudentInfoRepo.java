package com.example.AcademicHubBackend.Repository;

import com.example.AcademicHubBackend.Model.AdminStudentInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AdminStudentInfoRepo extends MongoRepository<AdminStudentInfo,String>{

}