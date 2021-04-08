package com.example.assignment_1;

import com.example.assignment_1.business.controller.UserController;
import com.example.assignment_1.business.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment1Application {


	public static void main(String[] args) {
		SpringApplication.run(Assignment1Application.class, args);
	}

}
