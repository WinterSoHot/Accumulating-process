package com.spring.ioc.Part4aop;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by gudongxian on 2017/3/22.
 */
@Configuration
@EnableAspectJAutoProxy
public class Config2 {

    @Bean
    public ArgsAspect aspect(){
        return  new ArgsAspect();
    }

    @Bean
    public BlackDisc blackDisc(){
        return new BlackDisc();
    }

    @Bean
    public DeclareAspectj declareAspectj(){
        return new DeclareAspectj();
    }

    @Bean("defaultPerform")
    public DefaultPerform defaultPerform(){
        return new DefaultPerform();
    }
}
