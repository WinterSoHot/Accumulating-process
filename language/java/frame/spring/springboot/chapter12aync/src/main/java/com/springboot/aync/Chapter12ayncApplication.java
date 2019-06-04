package com.springboot.aync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Chapter12ayncApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter12ayncApplication.class, args);
	}
}
