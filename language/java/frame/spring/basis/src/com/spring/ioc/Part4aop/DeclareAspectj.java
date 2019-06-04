package com.spring.ioc.Part4aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * Created by gudongxian on 2017/3/23.
 * 通过注解引入新o功能
 */
@Aspect
public class DeclareAspectj {
    @DeclareParents(value = "com.spring.ioc.Part4aop.Perform+",defaultImpl = DefaultPerform.class)
    public  static Perform perform;
}
