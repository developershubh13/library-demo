package com.example.librarydemo.service;

import com.example.librarydemo.model.Books;
import com.example.librarydemo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    public Books add(Books book){
        return booksRepository.save(book);
    }

    public List<Books> getAll(){
        return booksRepository.findAll();
    }

    public Optional<Books> getById(int id){
        return booksRepository.findById(id);
    }

    public List<Books> getByTitle(String title){
        return booksRepository.getByTitle(title);
    }

    public List<Books> getByAuthor(String author){
        return booksRepository.getByAuthor(author);
    }

    public void deleteById(int id){
        booksRepository.deleteById(id);
    }

    public Books update(Integer id,Books bookDetails){

        Optional<Books> book=booksRepository.findById(id);

            Books books =book.get();
            books.setAuthor(bookDetails.getAuthor());
            books.setNoOfCopies(bookDetails.getNoOfCopies());
            books.setTitle(bookDetails.getTitle());
            books.setStudents(bookDetails.getStudents());
            books.setTeachers(bookDetails.getTeachers());
            return booksRepository.save(books);
    }
}