package com.example.librarydemo.service;

import com.example.librarydemo.model.Books;
import com.example.librarydemo.model.Teachers;
import com.example.librarydemo.repository.BooksRepository;
import com.example.librarydemo.repository.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeachersService {

    @Autowired
    private TeachersRepository teachersRepository;

    @Autowired
    private BooksRepository booksRepository;

    public Teachers add(Teachers teachers){
        return teachersRepository.save(teachers);
    }

    public List<Teachers> getAllTeachers(){
        return teachersRepository.findAll();
    }

    public Optional<Teachers> getTeacherById(int id){
        
        return teachersRepository.findById(id);
    }

    public Teachers getByTeacherName(String name){
        return teachersRepository.getByTeacherName(name);
    }

    public List<Teachers> getTeacherBySubject(String subject){
        return teachersRepository.getTeacherBySubject(subject);
    }
    public void deleteById(int id){
        teachersRepository.deleteById(id);
    }

    public Teachers updateTeacher(int id,Teachers Teachers){
        Optional<Teachers> teachers=teachersRepository.findById(id);
        Teachers teacher1 = teachers.get();

        teacher1.setTeacherId(Teachers.getTeacherId());
        teacher1.setTeacherName(Teachers.getTeacherName());
        teacher1.setSubject(Teachers.getSubject());
        teacher1.setBooks(Teachers.getBooks());
        return teachersRepository.save(teacher1);
    }

    public List<Books> getBooksWithTeacher(int id){

        List<Books> books = new ArrayList<>();

        Optional<Teachers> teach = teachersRepository.findById(id);
        Teachers Teachers = teach.get();
        List<Integer> arr = Teachers.getBooks();

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

    public String issueBookById(int bookId,int teacherId){
        Optional<Books> b=booksRepository.findById(bookId);
        Books books=b.get();
        int noOfCopies=books.getNoOfCopies();

        Optional<Teachers> t=teachersRepository.findById(teacherId);
        if(!t.isPresent())
            throw new ValidationException("Teacher with TeacherID does not Exist");
        else {

            Teachers Teachers = t.get();
            List<Integer> arr = Teachers.getBooks();
            int size = arr.size();

            if (arr.contains(bookId)) {
                return "Book Already Issued";
            } else if (noOfCopies > 0 && (size < 5 && size>=0)) {
                books.setNoOfCopies(books.getNoOfCopies() - 1);
                arr.add(bookId);
                Teachers.setBooks(arr);
                booksRepository.save(books);
                teachersRepository.save(Teachers);
                return "Book Issued Successfully";
            } else if (size >= 5 && noOfCopies <=0) {
                return "Issue unsuccessfull!Already 5 Book Issued and No Of Copies is not enough ";
            } else if (noOfCopies <= 0) {
                return "Issue Unsuccessfull! No of Copies is not enough";
            } else if(size >= 5)
                return "Issue Unsuccessfull! Already 5 book Issued";
        }
        return "Issue Unsuccessfull";
    }


}
