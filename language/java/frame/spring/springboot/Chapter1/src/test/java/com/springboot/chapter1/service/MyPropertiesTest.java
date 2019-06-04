package com.springboot.chapter1.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyPropertiesTest {
    @Autowired
    MyProperties myProperties;

    @Test
    public void test1() throws Exception {
        System.out.println(myProperties);
    }
}