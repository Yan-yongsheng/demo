package com.demo.shop.service.impl;

import com.demo.shop.mapper.UserMapper;
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

    public int register(String account, String password, String category) {
        return userMapper.register(account, password,category);
    }

    public int login(String account, String password, String category) {
        return userMapper.login(account, password,category);
    }
}