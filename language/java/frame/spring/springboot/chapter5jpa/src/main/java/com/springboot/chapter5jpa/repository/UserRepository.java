package com.springboot.chapter5jpa.repository;

import com.springboot.chapter5jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 创建UserJPA仓库
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("select u from User u where u.name=:name")
    User findUser(@Param("name") String name);

    @Query("select u.name from User u where u.age = ?1")
    List<String> findNames(Integer age);
}
