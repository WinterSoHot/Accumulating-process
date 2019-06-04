package com.springboot.redisandcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Chapter17redisandcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter17redisandcacheApplication.class, args);
	}
}
