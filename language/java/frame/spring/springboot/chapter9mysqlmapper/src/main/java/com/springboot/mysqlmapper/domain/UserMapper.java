package com.springboot.mysqlmapper.domain;


import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user u WHERE u.name=#{name} ")
    User findByName(@Param("name") String name);


    @Insert("INSERT INTO user(name,age) VALUES(#{name},#{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);


    // 深入使用mybaits annotations

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT u.id,u.name,u.age FROM user u")
    List<User> findAll();

    @Update("UPDATE user SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(Long id);

    @Insert("INSERT INTO user(name, age) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);
}
