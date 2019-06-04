package com.spring.ioc.Part4aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by gudongxian on 2017/3/23.
 */
public class XmlAspect {

    public void before() {
        System.out.println("Aspect before");
    }

    public void after() {
        System.out.println("Aspect after");
    }

    public void afterReturn() {
        System.out.println("Aspect afterReturn");
    }

    public void afterThrow() {
        System.out.println("Aspect afterThrow");
    }

    public  void around(ProceedingJoinPoint jp){
        try {
            System.out.println("Around Before");
            jp.proceed();
            System.out.println("Around After");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Throw");
        }
    }
}
