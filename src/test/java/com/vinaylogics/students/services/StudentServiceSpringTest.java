package com.vinaylogics.students.services;

import com.vinaylogics.students.exceptions.BadRequestException;
import com.vinaylogics.students.models.Gender;
import com.vinaylogics.students.models.Student;
import com.vinaylogics.students.repositories.StudentRepository;
import com.vinaylogics.students.services.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class StudentServiceSpringTest {

    @MockBean
    private StudentRepository studentRepository;


    @Autowired
    private StudentService underTest;


    @Test
    void canGetAllStudents() {
        // when
        underTest.getAllStudents();
        // then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddStudent() {
        // given
        String email = "vinay@gmail.com";
        Student student = new Student(
                "vinay",
                email,
                Gender.MALE
        );

        // when
        underTest.addStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);
        verify(studentRepository)
                .save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void willThrowIfEmailIsTaken() {
        // given
        String email = "vinay@gmail.com";
        Student student = new Student(
                "vinay",
                email,
                Gender.MALE
        );
        given(studentRepository.selectExistsEmail(student.getEmail()))
                .willReturn(true);
        // when
        // then
        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail()+" taken.");
        verify(studentRepository, never()).save(any());
    }

    @Test
    @Disabled
    void deleteStudent() {
    }

}