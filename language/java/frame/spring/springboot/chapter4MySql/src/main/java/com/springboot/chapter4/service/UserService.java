package com.springboot.chapter4.service;

public interface UserService {


    void create(String name, Integer age);

    void deleteByName(String name);

    Integer getAllUsers();

    void deleteAllUsers();
}
