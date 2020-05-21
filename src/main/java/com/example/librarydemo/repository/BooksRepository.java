package com.example.librarydemo.repository;


import com.example.librarydemo.model.Books;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends MongoRepository<Books,Integer> {

    public List<Books> getByTitle(String title);
    public List<Books> getByAuthor(String title);

    @DeleteQuery("{'bookId' : ?0}")
    public void deleteId(int bookId);
}