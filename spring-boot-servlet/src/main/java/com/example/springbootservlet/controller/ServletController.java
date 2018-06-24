package com.example.springbootservlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/24 22:42
 */
@Controller
public class ServletController {
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "hello servlet!";
    }
}
