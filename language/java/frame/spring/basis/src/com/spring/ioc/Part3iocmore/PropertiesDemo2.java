package com.spring.ioc.Part3iocmore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by gudongxian on 2017/3/16.
 * 使用占位符
 */
@Configuration
@ComponentScan("com.spring.ioc.Part3iocmore.bean")
@PropertySource(value = "com/spring/ioc/test.properties", encoding = "UTF-8")
public class PropertiesDemo2 {

    @Bean
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
