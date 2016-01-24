package com.astraltear.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootEx1Application {
	
	@RequestMapping("/")
	String home() {
		return "Hello Spring BOOT World!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEx1Application.class, args);
	}
}
