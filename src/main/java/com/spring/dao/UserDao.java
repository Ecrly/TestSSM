package com.spring.dao;

import com.spring.base.BaseDao;
import com.spring.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao extends BaseDao {
    public List<User> findAll();
    public User findByName(String name);
    public void createUser(@Param("name") String name, @Param("password") String password,
                           @Param("email") String email);
}
