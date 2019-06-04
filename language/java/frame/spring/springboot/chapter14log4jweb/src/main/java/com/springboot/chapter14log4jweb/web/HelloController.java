package com.springboot.chapter14log4jweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gudongxian
 * @create 2017-11-30 下午9:42
 **/
@Controller
public class HelloController {

    @GetMapping("/")
    @ResponseBody
    public String hello(@RequestParam("name") String name){
        return "Hello,"+name;
    }
}
