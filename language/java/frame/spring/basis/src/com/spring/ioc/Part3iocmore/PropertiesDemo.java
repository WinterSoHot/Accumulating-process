package com.spring.ioc.Part3iocmore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by gudongxian on 2017/3/16.
 * 运行时注入值
 * Properties
 * Spring 表达式语言
 */
@Configuration
@PropertySource(value = "com/spring/ioc/test.properties",encoding = "UTF-8")
public class PropertiesDemo {
    @Autowired
    Environment env;

    @Bean(value = "user")
    public User getUser() {
        return new User(env.getProperty("my.name", "root"), env.getProperty("my.sex", "未知"));
    }
}
