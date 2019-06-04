package com.spring.ioc.Part3iocmore.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by gudongxian on 2017/3/16.
 */
@Component("account")
@Qualifier("account")
public class Account {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private  String name;
    private  String sex;
    //占位符
    public Account(@Value("${my.name}") String name,@Value("${my.sex}") String sex) {
        this.name = name;
        this.sex = sex;
    }
}
