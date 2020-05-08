package com.example.librarydemo.controller;


import com.example.librarydemo.model.Books;
import com.example.librarydemo.model.Students;
import com.example.librarydemo.repository.BooksRepository;
import com.example.librarydemo.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private BooksRepository booksRepository;


    @GetMapping("/")
    public String message(){
        return "Students Controller";
    }
    @PostMapping("/addStudent")
    public String addStudents(@RequestBody Students students){
        studentsRepository.save(students);
        return "Student Added Successfully";
    }

    @GetMapping("/getAllStudents")
    public List<Students> getAllStudents(){
        return studentsRepository.findAll();
    }

    @GetMapping("/getStudentById/{id}")
    public Optional<Students> getStudentById(@PathVariable int id){
        return studentsRepository.findById(id);
    }

    @GetMapping("/getAllBooks")
    public List<Books> getAllBooks(){
        return booksRepository.findAll();
    }

    @GetMapping("/getBookById/{id}")
    public Optional<Books> getBookById(@PathVariable int id){
        return booksRepository.findById(id);
    }

    @GetMapping("/getBookByTitle/{title}")
    public List<Books> getBookByTitle(@PathVariable String title){
        return booksRepository.getBookByTitle(title);
    }

    @PutMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable int id,@RequestBody Students students){

        Optional<Students> student=studentsRepository.findById(id);
        Students student1=student.get();

        student1.setStudentId(students.getStudentId());
        student1.setStudentName(students.getStudentName());
        student1.setStudentClass(students.getStudentClass());
        student1.setBooks(students.getBooks());

        studentsRepository.save(student1);
        return "Student Updated Successfully";
    }

    @GetMapping("/getBooksWithStudent/{id}")
    public List<Books> getBooksWithStudent(@PathVariable int id){

        List<Books> books = new ArrayList<Books>();

        Optional<Students> stud = studentsRepository.findById(id);
        Students students = stud.get();
        List<Integer> arr = students.getBooks();

        int i = 0;
        for (i = 0; i < arr.size(); i++) {
            Optional<Books> b = (booksRepository.findById(arr.get(i)));
            Books b1 = b.get();

            books.add(b1);
        }

        return books;

    }

    @GetMapping("/issueBookById/{bookId}/{studentId}")
    public String issueBookById(@PathVariable int bookId,@PathVariable int studentId)
    {
        Optional<Books> b=booksRepository.findById(bookId);
        Books books=b.get();
        int noOfCopies=books.getNoOfCopies();

        Optional<Students> s=studentsRepository.findById(studentId);
        Students students=s.get();
        List<Integer> arr=students.getBooks();
        int size=arr.size();

        if(arr.contains(bookId)){
            return "Book Already Issued";
        }else if(noOfCopies>0 && size<3){
            books.setNoOfCopies(books.getNoOfCopies()-1);
            arr.add(bookId);
            students.setBooks(arr);
            booksRepository.save(books);
            studentsRepository.save(students);
            return "Book Issued Successfully";
        }else if(size>=3){
            return "Already 3 Book Issued";
        }else if(noOfCopies<=0){
            return "No Copies Available";
        }else
            return "Issue Unsuccessfull";
    }

}
