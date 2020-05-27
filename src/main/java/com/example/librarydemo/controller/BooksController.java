package com.example.librarydemo.controller;


import com.example.librarydemo.model.Books;
import com.example.librarydemo.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksService bookService;


    //Adding a book
    @PostMapping("/add")
    public ResponseEntity<Books> add(@Valid @RequestBody Books book){
     return new ResponseEntity<Books>(bookService.add(book), HttpStatus.OK);

    }

    //Read all books
    @GetMapping("/getAll")
    public ResponseEntity<List<Books>> getAll(){
        return new ResponseEntity<List<Books>>(bookService.getAll(),HttpStatus.OK);
    }

    //Read a book with a given id
    @GetMapping("/getById/{id}")
    public Optional<Books> getById(@PathVariable int id){
        if(id<0){
            throw new ValidationException("Book ID cannot be negative");
        }
        if(!bookService.getById(id).isPresent())
            throw new ValidationException("Book with given ID not found");

        return bookService.getById(id);
    }

    //Read a book by title
    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<List<Books>> getByTitle(@PathVariable String title){
        return new ResponseEntity<>(bookService.getByTitle(title), HttpStatus.OK);
    }

    //Read a book by author
    @GetMapping("/getByAuthor/{author}")
    public ResponseEntity<List<Books>> getByAuthor(@PathVariable String author){
        return new ResponseEntity<>(bookService.getByAuthor(author), HttpStatus.OK);
    }

    //Delete book with a given ID
    @DeleteMapping("/deleteById/{id}")
    public void deleteBookById(@PathVariable int id){
        if(id<0)
            throw new ValidationException("Book ID can not be negative");
        else
         bookService.deleteById(id);
    }

    //Update a book with a given Id
    @PutMapping("/update/{id}")
    public ResponseEntity<Books> update(@PathVariable int id,@Valid @RequestBody Books bookDetails){

        if(id<0){
            throw new ValidationException("Book ID can not be negative");
        }else{
            return new ResponseEntity<>(bookService.update(id, bookDetails), HttpStatus.OK);
        }
    }
}

