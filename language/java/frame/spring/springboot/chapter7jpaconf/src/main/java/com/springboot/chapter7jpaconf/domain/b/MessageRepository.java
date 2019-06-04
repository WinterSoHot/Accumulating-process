package com.springboot.chapter7jpaconf.domain.b;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository  extends JpaRepository<Message,Long>{

    Message findByCode(Integer code);
}
