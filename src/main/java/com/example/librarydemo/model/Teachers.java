package com.example.librarydemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Document(collection = "Teachers")
public class Teachers {

    @Id
    private int teacherId;

    @NotBlank
    @Size(max=30)
    private String teacherName;

    @NotBlank
    @Size(max=30)
    private String subject;

    private List<Integer> books;

    public Teachers() {
    }

    public Teachers(int teacherId, String teacherName, String subject, List<Integer> books) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.subject = subject;
        this.books = books;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Integer> getBooks() {
        return books;
    }

    public void setBooks(List<Integer> books) {
        this.books = books;
    }
}
