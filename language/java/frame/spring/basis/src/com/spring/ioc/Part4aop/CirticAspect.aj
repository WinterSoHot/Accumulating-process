package com.spring.ioc.Part4aop;

/**
 * Created by gudongxian on 2017/3/23.
 * Aspectj 创建 切面
 */
public aspect CirticAspect {

    public CirticAspect() {
    }

    pointcut performance() : execution(* com.spring.ioc.Part4aop.Perform.perform(..));

    after() : performance(){
        System.out.println(engie.getCriticism());
    }

    private  Engie engie;

    public void setEngie(Engie engie) {
        this.engie = engie;
    }
}
