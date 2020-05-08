package com.example.librarydemo.controller;


import com.example.librarydemo.model.Books;
import com.example.librarydemo.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksRepository bookRepository;

    @PostMapping("/addBook")
    public String addBook(@RequestBody Books book){
        bookRepository.save(book);
        return "Book Added Successfully";
    }

    @GetMapping("/getAllBooks")
    public List<Books> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/getBookById/{id}")
    public Optional<Books> getBookById(@PathVariable int id){
        return bookRepository.findById(id);
    }

    @GetMapping("/getBookByTitle/{title}")
    public List<Books> getBooksByTitle(@PathVariable String title){
        return bookRepository.getBookByTitle(title);
    }

    @GetMapping("/getById/{title}")
    public List<Books> getById(@PathVariable String title){
        return bookRepository.getById(title);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public void deleteBookById(@PathVariable int id){
        bookRepository.deleteById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteId(@PathVariable int id){
        bookRepository.deleteId(id);
    }

    @PutMapping("/updateBook/{id}")
    public void updateBook(@PathVariable int id,@RequestBody Books bookDetails){
        Optional<Books> book=bookRepository.findById(id);  //Integer

        Books books=book.get();
        books.setAuthor(bookDetails.getAuthor());
        books.setNoOfCopies(bookDetails.getNoOfCopies());
        books.setTitle(bookDetails.getTitle());
        books.setStudents(bookDetails.getStudents());
        books.setTeachers(bookDetails.getTeachers());

        bookRepository.save(books);


    }
}

