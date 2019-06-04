package com.spring.ioc.Part3iocmore;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by gudongxian on 2017/3/15.
 * bean 的作用域
 * 默认为单例
 * sington
 * prototype
 * session Web
 * request
 */
@Configuration
public class BeanScopeConfig {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User user(){
        return  new User();
    }

}
