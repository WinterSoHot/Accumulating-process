package com.spring.ioc.Part4aop;

/**
 * Created by gudongxian on 2017/3/23.
 */
public class CirticEngie implements Engie {

    String [] pool;

    public void setPool(String[] pool) {
        this.pool = pool;
    }

    @Override
    public String getCriticism() {
        int i = (int) (Math.random()*pool.length);
        return pool[i];
    }
}
