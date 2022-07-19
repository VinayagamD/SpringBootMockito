package com.vinaylogics.students.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vinaylogics.students.models.Gender;
import com.vinaylogics.students.models.Student;
import com.vinaylogics.students.services.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Test
    void testController() throws Exception {
        // given
        String email = "vinay@gmail.com";
        List<Student> expected = new ArrayList<>();
        expected.add( new Student(
                "vinay",
                email,
                Gender.MALE)
        );
        given(studentService.getAllStudents()).willReturn(expected);

        // when
        MvcResult result = mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        List<Student> actual = MAPPER.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Student>>(){});
        assertThat(actual).isEqualTo(expected);
    }


}