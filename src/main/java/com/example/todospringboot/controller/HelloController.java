package com.example.todospringboot.controller;

import com.example.todospringboot.model.Greeting;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@CrossOrigin(origins = "http://localhost:4200")
public class HelloController {

    @GetMapping("hello")
    public Greeting greetMessage() {
        String message = "Hello World !";
        Greeting greeting = new Greeting();
        greeting.setMessage(message);
//        throw new RuntimeException("Some error has happened. Please contact to support team...");
        return greeting;
    }

    @GetMapping("basicauth")
    public Greeting authentication() {
        String message = "You are authenticated.";
        Greeting greeting = new Greeting();
        greeting.setMessage(message);
//        throw new RuntimeException("Some error has happened. Please contact to support team...");
        return greeting;
    }
}
