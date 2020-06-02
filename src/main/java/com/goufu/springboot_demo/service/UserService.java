package com.goufu.springboot_demo.service;

import com.goufu.springboot_demo.entity.User;
import com.goufu.springboot_demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserMapper userMapper;
    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findUserByName(String userName) {
        return userMapper.findUserByName(userName);
    }

    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }
}
