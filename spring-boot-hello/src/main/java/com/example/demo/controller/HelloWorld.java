package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    // http://localhost:8080
    @RequestMapping("/")
    public String sayHello() {
        return "Hello World!";
    }
}
