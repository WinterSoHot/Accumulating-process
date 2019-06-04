package com.spring.ioc.Part4aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gudongxian on 2017/3/22.
 */
@Aspect
public class ArgsAspect {
    Map<Integer,Integer> map = new HashMap<>();

    @Pointcut("execution(* com.spring.ioc.Part4aop.BlackDisc.play(int))" +"&& args(num)")
    public void playpointcut(int num){};

    @Before("playpointcut(num)")
    public void sout(int num){
        int count = getPlayCount(num);
        map.put(num,count+1);
    }

    public int getPlayCount(int num){
        return map.containsKey(num) ? map.get(num) : 0;
    }
}
