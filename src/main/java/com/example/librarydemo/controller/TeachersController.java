package com.example.librarydemo.controller;


import com.example.librarydemo.model.Books;
import com.example.librarydemo.model.Teachers;
import com.example.librarydemo.repository.BooksRepository;
import com.example.librarydemo.repository.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers/")
public class TeachersController {

    @Autowired
    private TeachersRepository teachersRepository;

    @Autowired
    private BooksRepository booksRepository;

    @PostMapping("/addTeachers")
    public String addTeachers(@RequestBody Teachers teachers){

        teachersRepository.save(teachers);
        return "Teachers added successfully";
    }

    @GetMapping("/getAllTeachers")
    public List<Teachers> getAllTeachers(){
        return teachersRepository.findAll();
    }


    @GetMapping("/getTeacherById/{id}")
    public Optional<Teachers> getTeacherById(@PathVariable int id){
        return teachersRepository.findById(id);
    }




    @GetMapping("/getAllBooks")
    public List<Books> getAllBooks(){
        return booksRepository.findAll();
    }

    @GetMapping("/getBookById/{id}")
    public Optional<Books> getBookById(@PathVariable  int id){
        return booksRepository.findById(id);
    }

    @GetMapping("/getBookByTitle/{title}")
    public List<Books> getBookById(@PathVariable String title){
        return booksRepository.getBookByTitle(title);
    }

    @DeleteMapping("/deleteTeacherById/{id}")
    public String deleteTeacherById(@PathVariable int id){
        teachersRepository.deleteById(id);
        return "Teacher Deleted Successfully";
    }

    @PutMapping("/updateTeacherById/{id}")
    public String updateTeacherById(@PathVariable int id,@RequestBody Teachers teachers){

        Optional<Teachers> t=teachersRepository.findById(id);
        if (!t.isPresent()){
            throw new ValidationException("Teacher ID not present");
        }
        Teachers teachers1=t.get();
        teachers1.setTeacherId(teachers.getTeacherId());
        teachers1.setTeacherName(teachers.getTeacherName());
        teachers1.setSubject(teachers.getSubject());
        teachers.setBooks(teachers.getBooks());

        teachersRepository.save(teachers1);

        return "Update Successfull";
    }


    @GetMapping("/getBooksWithTeacher/{id}")
    public List<Books> getBooksWithTeacher(@PathVariable int id){

        List<Books> books=new ArrayList<>();

        Optional<Teachers> t=teachersRepository.findById(id);
        Teachers teachers=t.get();
        List<Integer> arr=teachers.getBooks();

        int i=0;
        for(i=0;i<arr.size();i++){
            Optional<Books> b=booksRepository.findById(arr.get(i));
            Books b1=b.get();
            books.add(b1);
        }

        return books;
    }


    @GetMapping("/issueBookById/{bookId}/{teacherId}")
    public String issueBookById(@PathVariable int bookId,@PathVariable int teacherId)
    {
        Optional<Books> b=booksRepository.findById(bookId);
        Books books=b.get();
        int noOfCopies=books.getNoOfCopies();

        Optional<Teachers> t=teachersRepository.findById(teacherId);
        Teachers teachers=t.get();
        List<Integer> arr=teachers.getBooks();
        int size=arr.size();

        if(arr.contains(bookId)){
            return "Book Already Issued";
        }else if(noOfCopies>0 && size<5){
            books.setNoOfCopies(books.getNoOfCopies()-1);
            arr.add(bookId);
            teachers.setBooks(arr);
            booksRepository.save(books);
            teachersRepository.save(teachers);
            return "Book Issued Successfully";
        }else if(size>=5){
            return "Already 5 Book Issued";
        }else if(noOfCopies<=0){
            return "No Copies Available";
        }else
            return "Issue Unsuccessfull";
    }

}
