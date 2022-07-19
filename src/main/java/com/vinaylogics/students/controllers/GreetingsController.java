package com.vinaylogics.students.controllers;

import com.vinaylogics.students.models.Message;
import com.vinaylogics.students.services.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/greetings")
@RequiredArgsConstructor
public class GreetingsController {

    private final GreetingService service;

    @GetMapping
    public Message greet(){
        return service.greet();
    }
}
