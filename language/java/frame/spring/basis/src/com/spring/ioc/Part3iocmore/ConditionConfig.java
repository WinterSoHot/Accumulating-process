package com.spring.ioc.Part3iocmore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gudongxian on 2017/3/15.
 * 条件化创建Bean
 */
@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(MyDemoCondition.class)
    public  User getUser(){
        User user = new User();
        user.setName("Demo");
        return user;
    }

}
