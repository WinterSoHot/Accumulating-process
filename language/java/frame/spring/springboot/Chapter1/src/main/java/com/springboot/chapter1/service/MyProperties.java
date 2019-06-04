package com.springboot.chapter1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyProperties {

    @Value("${com.springboot.chapter1.value}")
    private String value;

    @Value("${com.springboot.chapter1.number}")
    private Integer number;

    @Value("${com.springboot.chapter1.bignumber}")
    private Long bigNumber;

    @Value("${com.springboot.chapter1.test}")
    private Integer test;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getBigNumber() {
        return bigNumber;
    }

    public void setBigNumber(Long bigNumber) {
        this.bigNumber = bigNumber;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "MyProperties{" +
                "value='" + value + '\'' +
                ", number=" + number +
                ", bigNumber=" + bigNumber +
                ", test=" + test +
                '}';
    }
}
