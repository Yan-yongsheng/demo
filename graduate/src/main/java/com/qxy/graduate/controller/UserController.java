package com.qxy.graduate.controller;

import com.qxy.graduate.entity.User;
import com.qxy.graduate.service.impl.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:29
 */
@RestController
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/register/")
    @CrossOrigin("*")
    String register(@RequestBody User user) {
        System.out.println("有人请求注册！");
        int res = userService.register(user.getAccount(), user.getPassword());
        if(res==1) {
            return "注册成功";
        } else {
            return "注册失败";
        }
    }

    @PostMapping("/login/")
    @CrossOrigin("*")
    String login(@RequestBody User user) {
        int res = userService.login(user.getAccount(), user.getPassword());
        if(res==1) {
            return "登录成功";
        } else {
            return "登录失败";
        }
    }
}
