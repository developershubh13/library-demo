package com.example.librarydemo.repository;

import com.example.librarydemo.model.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends MongoRepository<Students,Integer> {

    public Students getByStudentName(String name);
}