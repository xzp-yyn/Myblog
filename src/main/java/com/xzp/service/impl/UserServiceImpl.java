package com.xzp.service.impl;

import com.xzp.dao.UserDao;
import com.xzp.entity.User;
import com.xzp.service.UserService;
import com.xzp.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author xuezhanpeng
 * @Date 2022/9/26 8:49
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.findBynameandpassword(username, MD5Util.code(password));
    }
}
