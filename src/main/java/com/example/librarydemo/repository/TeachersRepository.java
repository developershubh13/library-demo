package com.example.librarydemo.repository;

import com.example.librarydemo.model.Teachers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachersRepository extends MongoRepository<Teachers,Integer> {
    Teachers getByTeacherName(String name);

    List<Teachers> getTeacherBySubject(String subject);
}
