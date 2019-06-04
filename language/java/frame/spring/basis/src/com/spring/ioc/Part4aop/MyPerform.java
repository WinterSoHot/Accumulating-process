package com.spring.ioc.Part4aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by gudongxian on 2017/3/22.
 */
@Component("perform")
public class MyPerform implements  Perform{
    @Override
    public void perform() {
        System.out.println("My perform");
    }

    @Override
    public void Around() {
        System.out.println("Hahaha");
    }
}
