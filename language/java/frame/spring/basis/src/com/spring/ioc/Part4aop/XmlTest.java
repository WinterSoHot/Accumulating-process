package com.spring.ioc.Part4aop;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gudongxian on 2017/3/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "aoptest1.xml")
public class XmlTest {

    @Autowired
    Perform perform;

    @org.junit.Test
    public void test(){
        perform.perform();
    }
}
