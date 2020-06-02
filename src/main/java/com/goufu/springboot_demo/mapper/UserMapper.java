package com.goufu.springboot_demo.mapper;

import com.goufu.springboot_demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findUserById(String userId);
    User findUserByName(String userName);
}
