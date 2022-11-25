package com.example.AcademicHubBackend.repository;
import com.example.AcademicHubBackend.model.AdminTeacherInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminTeacherInfoRepo  extends MongoRepository<AdminTeacherInfo,String>{

}
