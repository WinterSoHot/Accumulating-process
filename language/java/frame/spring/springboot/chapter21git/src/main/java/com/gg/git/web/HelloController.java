package com.gg.git.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gudongxian
 * @create 2017-12-18 下午5:06
 **/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
