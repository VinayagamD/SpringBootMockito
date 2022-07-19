package com.vinaylogics.students.services;

import com.vinaylogics.students.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void addStudent(Student student);
    void deleteStudent(Long studentId);
}
