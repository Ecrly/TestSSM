package com.spring.service;

import com.spring.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public User findByName(String name);
    public List<User> findAll();
    public void createUser(String name, String password, String email);
}
