package com.qxy.graduate.service.impl;

import com.qxy.graduate.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:30
 */
@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public int register(String account, String password) {
        return userMapper.register(account, password);
    }

    public int login(String account, String password) {
        return userMapper.login(account, password);
    }
}