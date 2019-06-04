package com.spring.ioc.Part4aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by gudongxian on 2017/3/22.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class Config {

    @Bean
    public MyAspect myyAspect(){
        return new MyAspect();
    }
}
