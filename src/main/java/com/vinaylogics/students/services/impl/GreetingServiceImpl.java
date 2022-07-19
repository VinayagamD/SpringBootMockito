package com.vinaylogics.students.services.impl;

import com.vinaylogics.students.models.Message;
import com.vinaylogics.students.services.GreetingService;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public Message greet() {
        return new Message("Hello, World");
    }
}
