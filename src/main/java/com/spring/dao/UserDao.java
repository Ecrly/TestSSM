package com.spring.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public String findByName(String name);
}
