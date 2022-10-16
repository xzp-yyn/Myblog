package com.xzp.service;

import com.xzp.entity.User;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 8:48
 * @Version 1.0
 */
public interface UserService {
    User checkUser(String username,String password);
}
