package com.springboot.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 在Spring Boot的众多Starter POMs中有一个特殊的模块，
 * 它不同于其他模块那样大多用于开发业务功能或是连接一些其他外部资源。
 * 它完全是一个用于暴露自身信息的模块，所以很明显，它的主要作用是用于监控与管理，
 * 它就是：spring-boot-starter-actuator。
 */
@RestController
@SpringBootApplication
public class Chapter15actuatorApplication {

    private Logger logger = LoggerFactory.getLogger(Chapter15actuatorApplication.class);

    @GetMapping("/test")
    public String testLevel() {
        logger.info("LEVEL INFO");
        logger.debug("LEVEL DEBUG");
        logger.error("LEVLE ERROR");
        return "";
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter15actuatorApplication.class, args);
    }
}
