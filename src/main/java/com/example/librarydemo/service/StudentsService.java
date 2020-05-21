package com.example.librarydemo.service;

import com.example.librarydemo.model.Books;
import com.example.librarydemo.model.Students;
import com.example.librarydemo.model.Teachers;
import com.example.librarydemo.repository.BooksRepository;
import com.example.librarydemo.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private BooksRepository booksRepository;

    public Students add(Students students){
        return studentsRepository.save(students);
    }

    public List<Students> getAllStudents(){
        return studentsRepository.findAll();
    }

    public Optional<Students> getStudentById(int id){
        return studentsRepository.findById(id);
    }

    public Students getByStudentName(String name){
        return studentsRepository.getByStudentName(name);
    }

    public void deleteById(int id){
        studentsRepository.deleteById(id);
    }

    public Students updateStudent(int id,Students students){
        Optional<Students> student=studentsRepository.findById(id);
        Students student1 = student.get();

        student1.setStudentId(students.getStudentId());
        student1.setStudentName(students.getStudentName());
        student1.setStudentClass(students.getStudentClass());
        student1.setBooks(students.getBooks());
        return studentsRepository.save(student1);
    }

    public List<Books> getBooksWithStudent(int id){

        List<Books> books = new ArrayList<>();

        Optional<Students> stud = studentsRepository.findById(id);
        Students students = stud.get();
        List<Integer> arr = students.getBooks();

        if(arr.size()==0){

            throw new ValidationException("No Books Issued");
        }else {

            for (int i = 0; i < arr.size(); i++) {
                Optional<Books> b = (booksRepository.findById(arr.get(i)));
                if (!b.isPresent())
                    throw new ValidationException("Book doesn't exist");
                Books b1 = b.get();
                books.add(b1);
            }
        }

        return books;
    }

    public String issueBookById(int bookId,int studentId){
        Optional<Books> b=booksRepository.findById(bookId);
        Books books=b.get();
        int noOfCopies=books.getNoOfCopies();

        Optional<Students> s=studentsRepository.findById(studentId);
        if(!s.isPresent())
            throw new ValidationException("Student with StudentID does not Exist");
        else {

            Students students = s.get();
            List<Integer> arr = students.getBooks();
            int size = arr.size();

            if (arr.contains(bookId)) {
                return "Book Already Issued";
            } else if (noOfCopies > 0 && (size < 3 && size>=0)) {
                books.setNoOfCopies(books.getNoOfCopies() - 1);
                arr.add(bookId);
                students.setBooks(arr);
                booksRepository.save(books);
                studentsRepository.save(students);
                return "Book Issued Successfully";
            } else if (size >= 3 && noOfCopies <=0) {
                return "Issue unsuccessfull!Already 3 Book Issued and No Of Copies is not enough ";
            } else if (noOfCopies <= 0) {
                return "Issue Unsuccessfull! No of Copies is not enough";
            } else if(size >= 3)
                return "Issue Unsuccessfull! Already 3 book Issued";
        }
        return "Issue Unsuccessfull";
    }



}


