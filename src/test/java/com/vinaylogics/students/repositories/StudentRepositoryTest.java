package com.vinaylogics.students.repositories;

import com.vinaylogics.students.models.Gender;
import com.vinaylogics.students.models.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldCheckIfStudentExistsEmail() {
        // given
        String email = "vinay@gmail.com";
        Student student = new Student(
                "vinay",
                email,
                Gender.MALE
        );
        underTest.save(student);

        // when
         boolean expected = underTest.selectExistsEmail(email);

         // then
        assertThat(expected).isTrue();

    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        // given
        String email = "vinay@gmail.com";
        Student student = new Student(
                "vinay",
                email,
                Gender.MALE
        );
        // when
         boolean expected = underTest.selectExistsEmail(email);

         // then
        assertThat(expected).isFalse();

    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }
}