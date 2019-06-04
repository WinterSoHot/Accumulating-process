package com.springboot.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 数据库缓存的使用
 */
@SpringBootApplication
@EnableCaching
public class Chapter16cacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chapter16cacheApplication.class, args);
	}
}
