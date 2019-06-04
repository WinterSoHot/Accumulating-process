package com.spring.ioc.Part4aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by gudongxian on 2017/3/18.
 * 定义 我的切点
 */
@Aspect
public class MyAspect {


    @Pointcut("execution(* com.spring.ioc.Part4aop.Perform.perform(..))")
    public void performance(){}

    @Before("performance()")
    public void perpeare(){
        System.out.println("Aspectj Before");
    }

    @AfterReturning("performance()")
    public void after(){
        System.out.println("Aspectj AfterReturning");
    }

    @AfterThrowing("performance()")
    public void AfterThrowing(){
        System.out.println("Aspectj AfterThrowing");
    }
    @Around("execution(* com.spring.ioc.Part4aop.Perform.Around(..))")
    public void  arount(ProceedingJoinPoint jp){
        try {

            System.out.println("Around Before");
            jp.proceed();
            System.out.println("Around After");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
