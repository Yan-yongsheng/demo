package com.demo.shop.controller;

import com.demo.shop.common.ReturnData;
import com.demo.shop.common.StateCode;
import com.demo.shop.entity.OrderTotal;
import com.demo.shop.entity.User;
import com.demo.shop.service.SubmitCommentService;
import com.demo.shop.service.impl.UserService;
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
    @Resource
    SubmitCommentService submitCommentService;

    @PostMapping("/register/")
    @CrossOrigin("*")
    ReturnData register(@RequestBody User user) {
        try {
            int res = userService.register(user.getAccount(), user.getPassword(), user.getCategory());
            logger.info("注册 账号：{} 密码：{} 类别 :{}",user.getAccount(),user.getPassword(), user.getCategory());
            if(res==1) {
                return new ReturnData<>(StateCode.SUCCESS.getCode(),
                        StateCode.SUCCESS.getMsg(), "注册成功");
            } else {
                return new ReturnData<>(StateCode.FAIL.getCode(),
                        StateCode.FAIL.getMsg(), "注册失败");
            }
        }catch (Exception e){
            logger.error("[register] :{}",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "注册失败");
        }

    }

    @PostMapping("/login/")
    @CrossOrigin("*")
    ReturnData login(@RequestBody User user) {
        try {
            int res = userService.login(user.getAccount(), user.getPassword(), user.getCategory());
            logger.info("登录 账号：{} 密码：{} 类别： {} 结果:{}",user.getAccount(),user.getPassword(), user.getCategory(),res);

            if(res==1) {
                return new ReturnData<>(StateCode.SUCCESS.getCode(),
                        StateCode.SUCCESS.getMsg(), "登录成功");
            } else {
                return new ReturnData<>(StateCode.FAIL.getCode(),
                        StateCode.FAIL.getMsg(), "登录失败");
            }
        }catch (Exception e){
            logger.error("[login] :{}",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "登录失败");
        }

    }
    //提交评分
    @PostMapping("/submit/comment/")
    @CrossOrigin("*")
    ReturnData submitComment(@RequestBody OrderTotal orderTotal) {
        try {
            submitCommentService.submitComment(orderTotal.getOrderId(),orderTotal.getServiceIdList(),orderTotal.getUserId(),
                    orderTotal.getQualityScore(),orderTotal.getSpeedScore(),orderTotal.getAttitudeScore(),orderTotal.getComment());
            return new ReturnData<>(StateCode.SUCCESS.getCode(),
                    StateCode.SUCCESS.getMsg(), "提交评价成功");

        }catch (Exception e){
            logger.error("[submitComment] :{}",e);
            return new ReturnData<>(StateCode.FAIL.getCode(),
                    StateCode.FAIL.getMsg(), "提交评价失败");
        }

    }
}
