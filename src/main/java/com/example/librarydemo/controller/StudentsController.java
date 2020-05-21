package com.example.librarydemo.controller;

import com.example.librarydemo.model.Books;
import com.example.librarydemo.model.Students;
import com.example.librarydemo.service.BooksService;
import com.example.librarydemo.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.*;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private BooksService booksService;

    //Add a Student
    @PostMapping("/add")
    public ResponseEntity<Students> add(@Valid @RequestBody Students students){
       return new ResponseEntity<>(studentsService.add(students), HttpStatus.OK);
    }

    //Read all students
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Students>> getAllStudents(){
        return new ResponseEntity<>(studentsService.getAllStudents(),HttpStatus.OK);
    }

    //Read a student with given Id
    @GetMapping("/getStudentById/{id}")
    public Optional<Students> getStudentById(@PathVariable int id){

        if(id<0){
            throw new ValidationException("Student ID cannot be negative");
        }
        if(!studentsService.getStudentById(id).isPresent())
            throw new ValidationException("Student with given ID not found");

        return studentsService.getStudentById(id);
    }

     //Read Students with given name
     @GetMapping("/getByStudentName/{name}")
     public ResponseEntity<Students> getByStudentName(@PathVariable String name){
        return new ResponseEntity<>(studentsService.getByStudentName(name),HttpStatus.OK);
     }



    //Delete student with a given ID
    @DeleteMapping("/deleteById/{id}")
    public void deleteStudentById(@PathVariable int id){
        if(id<0)
            throw new ValidationException("Students ID can not be negative");
        else
            studentsService.deleteById(id);
    }

    //Update a Student with given Id
    @PutMapping("/update/{id}")
    public ResponseEntity<Students> updateStudent(@PathVariable int id,@Valid @RequestBody Students students){

        if(id<0)
            throw new ValidationException("Sudnet ID cannot be negative");
        else
            return new ResponseEntity<>(studentsService.updateStudent(id,students),HttpStatus.OK);
    }

    //Get All Books
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Books>> getAllBooks(){
        return new ResponseEntity<>(booksService.getAll(),HttpStatus.OK);
    }

    //Read Book with given Id
    @GetMapping("/getBookById/{id}")
    public Optional<Books> getBookById(@PathVariable int id){
        return booksService.getById(id);
    }

    //Read Book with given title
    @GetMapping("/getBookByTitle/{title}")
    public ResponseEntity<List<Books>> getBookByTitle(@PathVariable String title){
         return new ResponseEntity<>(booksService.getByTitle(title),HttpStatus.OK);
    }

    //Read Book with given author
    @GetMapping("/getBookByAuthor/{author}")
    public ResponseEntity<List<Books>> getBookByAuthor(@PathVariable String author){
        return new ResponseEntity<>(booksService.getByAuthor(author),HttpStatus.OK);
    }

    //Search All Books with student of given studentID
    @GetMapping("/getBooksWithStudent/{studentId}")
    public ResponseEntity<List<Books>> getBooksWithStudent(@PathVariable int studentId){

        Optional<Students> stud=studentsService.getStudentById(studentId);
        if(!stud.isPresent())
            throw new ValidationException("No Student with given StudentID");
        else{
            return new ResponseEntity<>(studentsService.getBooksWithStudent(studentId),HttpStatus.OK);
        }
    }

    //Issue Book with given bookID for student with given studentID
    @GetMapping("/issueBookById/{bookId}/{studentId}")
    public ResponseEntity<String> issueBookById(@PathVariable int bookId,@PathVariable int studentId) {
        Optional<Books> b = booksService.getById(bookId);
        if (!b.isPresent())
            throw new ValidationException("No Book With given BookID");
        else {
            if (bookId < 0 || studentId < 0)
                throw new ValidationException("Either bookID or studentID is negative");
            else {
                return new ResponseEntity<>(studentsService.issueBookById(bookId, studentId), HttpStatus.OK);
            }
        }
    }

}
