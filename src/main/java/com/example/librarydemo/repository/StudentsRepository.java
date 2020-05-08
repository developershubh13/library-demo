package com.example.librarydemo.repository;

import com.example.librarydemo.model.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends MongoRepository<Students,Integer> {

}