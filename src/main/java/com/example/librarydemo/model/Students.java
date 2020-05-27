package com.example.librarydemo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Document(collection = "Students")
public class Students {

    @Id
    private Integer studentId;

    @NotBlank
    @Size(max=30)
    private String studentName;

    @NotBlank
    @Size(max=30)
    private String studentClass;

    private Map<Integer,String> books;

    public Students(){

    }

    public Students(int studentId, String studentName, String studentClass,Map<Integer,String> books) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.books = books;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Map<Integer,String> getBooks() {
        return books;
    }

    public void setBooks(Map<Integer,String> books) {
        this.books = books;
    }
}
