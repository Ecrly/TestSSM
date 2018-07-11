package com.spring.service.impl;

import com.spring.dao.UserDao;
import com.spring.pojo.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public User findByName(String name) {
        return (User)userDao.findByName(name);
    }


    public void createUser(String name, String password, String email) {
        userDao.createUser(name, password, email);
    }


    public List<User> findAll() {
        return userDao.findAll();
    }
}
