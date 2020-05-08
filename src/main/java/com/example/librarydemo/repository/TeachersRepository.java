package com.example.librarydemo.repository;

import com.example.librarydemo.model.Teachers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends MongoRepository<Teachers,Integer> {
}
