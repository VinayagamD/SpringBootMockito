package com.vinaylogics.students.controllers;

import com.vinaylogics.students.models.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/message")
public class HelloController {

    @GetMapping
    public @ResponseBody String greeting(){
        return "Hello, World";
    }
}
