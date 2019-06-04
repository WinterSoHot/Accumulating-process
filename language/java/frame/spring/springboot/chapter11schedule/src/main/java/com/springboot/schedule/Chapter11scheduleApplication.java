package com.springboot.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Chapter11scheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter11scheduleApplication.class, args);
	}
}
