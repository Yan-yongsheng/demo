package com.qxy.graduate.controller;

import com.qxy.graduate.common.ReturnData;
import com.qxy.graduate.common.StateCode;
import com.qxy.graduate.entity.User;
import com.qxy.graduate.service.impl.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:29
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    UserService userService;

    @PostMapping("/register/")
    @CrossOrigin("*")
    ReturnData register(@RequestBody User user) {
        System.out.println("有人请求注册！");
        int res = userService.register(user.getAccount(), user.getPassword(), user.getCategory());
        logger.info("注册 账号：{} 密码：{} 类别 :{}",user.getAccount(),user.getPassword(), user.getCategory());
        if(res==1) {
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "注册成功");
        } else {
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "注册失败");
        }
    }

    @PostMapping("/login/")
    @CrossOrigin("*")
    ReturnData login(@RequestBody User user) {
        int res = userService.login(user.getAccount(), user.getPassword(), user.getCategory());
        logger.info("登录 账号：{} 密码：{} 类别： {} 结果:{}",user.getAccount(),user.getPassword(), user.getCategory(),res);

        if(res==1) {
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "登录成功");
        } else {
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "登录失败");
        }
    }
}
