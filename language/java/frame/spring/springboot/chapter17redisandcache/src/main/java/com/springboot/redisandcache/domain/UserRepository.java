package com.springboot.redisandcache.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User,Long> {

    //根据名字 判断你是否从缓存中取出数据
    @Cacheable(key = "#p0")
    User findByName(String name);

    @Cacheable(key = "#p0.name")
    User save(User user);
}
