package com.example.javaee;

import com.example.javaee.btrace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJavaEeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJavaEeApplication.class, args);
		//UserService.sayHello("ZHANGSAN",18);
	}
}
