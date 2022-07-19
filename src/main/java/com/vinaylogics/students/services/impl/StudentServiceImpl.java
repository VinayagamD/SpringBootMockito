package com.vinaylogics.students.services.impl;

import com.vinaylogics.students.exceptions.BadRequestException;
import com.vinaylogics.students.exceptions.StudentNotFoundException;
import com.vinaylogics.students.models.Student;
import com.vinaylogics.students.repositories.StudentRepository;
import com.vinaylogics.students.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        Boolean existsEmail = repository.selectExistsEmail(student.getEmail());
        if (existsEmail) {
            throw new BadRequestException("Email " + student.getEmail()+" taken.");
        }

        repository.save(student);

    }

    @Override
    public void deleteStudent(Long studentId) {
       if(! repository.existsById(studentId) ){
           throw new StudentNotFoundException("Student with id "+ studentId + " does not exists.");
       }
       repository.deleteById(studentId);
    }
}
