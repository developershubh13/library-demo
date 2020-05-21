package com.example.librarydemo.controller;

import com.example.librarydemo.model.Books;
import com.example.librarydemo.model.Teachers;
import com.example.librarydemo.service.BooksService;
import com.example.librarydemo.service.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers/")
public class TeachersController {

        @Autowired
        private TeachersService teachersService;

        @Autowired
        private BooksService booksService;

        //Add a Teacher
        @PostMapping("/add")
        public ResponseEntity<Teachers> add(@Valid @RequestBody Teachers teachers){
            return new ResponseEntity<>(teachersService.add(teachers), HttpStatus.OK);
        }

        //Read all Teachers
        @GetMapping("/getAllTeachers")
        public ResponseEntity<List<Teachers>> getAllTeachers(){
            return new ResponseEntity<>(teachersService.getAllTeachers(),HttpStatus.OK);
        }

        //Read a teacher with given Id
        @GetMapping("/getTeacherById/{id}")
        public Optional<Teachers> getTeacherById(@PathVariable int id){

            if(id<0){
                throw new ValidationException("Teacher ID cannot be negative");
            }
            if(!teachersService.getTeacherById(id).isPresent())
                throw new ValidationException("Teacher with given ID not found");

            return teachersService.getTeacherById(id);
        }

        //Read Teacher with given name
        @GetMapping("/getByTeacherName/{name}")
        public ResponseEntity<Teachers> getByTeacherName(@PathVariable String name){
            return new ResponseEntity<>(teachersService.getByTeacherName(name),HttpStatus.OK);
        }

        //Read all teacher of given Subject
        @GetMapping("/getTeacherBySubject/{subject}")
        public ResponseEntity<List<Teachers>> getTeacherBySubject(@PathVariable String subject){
            return new ResponseEntity<>(teachersService.getTeacherBySubject(subject),HttpStatus.OK);
        }


        //Delete Teacher with a given ID
        @DeleteMapping("/deleteById/{id}")
        public void deleteTeacherById(@PathVariable int id){
            if(id<0)
                throw new ValidationException("Teacher ID can not be negative");
            else
                teachersService.deleteById(id);
        }

        //Update a Teacher with given Id
        @PutMapping("/update/{id}")
        public ResponseEntity<Teachers> updateTeachers(@PathVariable int id,@Valid @RequestBody Teachers teachers){

            if(id<0)
                throw new ValidationException("Teacher ID cannot be negative");
            else
                return new ResponseEntity<>(teachersService.updateTeacher(id,teachers),HttpStatus.OK);
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

        //Search All Books with teacher of given teacherID
        @GetMapping("/getBooksWithTeacher/{teacherId}")
        public ResponseEntity<List<Books>> getBooksWithStudent(@PathVariable int teacherId){

            Optional<Teachers> teach=teachersService.getTeacherById(teacherId);
            if(!teach.isPresent())
                throw new ValidationException("No Student with given StudentID");
            else{
                return new ResponseEntity<>(teachersService.getBooksWithTeacher(teacherId),HttpStatus.OK);
            }
        }

        //Issue Book with given bookID for teacher with given teacherID
        @GetMapping("/issueBookById/{bookId}/{teacherId}")
        public ResponseEntity<String> issueBookById(@PathVariable int bookId,@PathVariable int teacherId) {
            Optional<Books> b = booksService.getById(bookId);
            if (!b.isPresent())
                throw new ValidationException("No Book With given BookID");
            else {
                if (bookId < 0 || teacherId < 0)
                    throw new ValidationException("Either bookID or teacherId is negative");
                else {
                    return new ResponseEntity<>(teachersService.issueBookById(bookId, teacherId), HttpStatus.OK);
                }
            }
        }

}


