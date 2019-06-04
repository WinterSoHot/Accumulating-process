package com.springboot.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author gudongxian
 * @create 2017-12-01 下午9:44
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll() //允许所有用户访问
                .anyRequest().authenticated() //其他需要认证
                .and()
                .formLogin()  //表单登录
                .loginPage("/login") //登录的地址  认证失败 重定向 /login?error
                .permitAll()
                .and()
                .logout() //登出 重定向 /login?logout
                .permitAll();
    }
//不配置内存用户默认用户名为user
//    /**
//     * 配置内存用户
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()//记忆认证
//                .withUser("user").password("password").roles("USER");
//    }
}
