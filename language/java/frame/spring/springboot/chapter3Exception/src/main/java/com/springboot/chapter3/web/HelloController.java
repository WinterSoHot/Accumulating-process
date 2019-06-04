package com.springboot.chapter3.web;

import com.springboot.chapter3.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("Error");
    }

    @GetMapping("/json")
    public String json() throws MyException {
        throw new MyException("Some Error");
    }

    @GetMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "gudongxian");
        return "index";
    }
}
