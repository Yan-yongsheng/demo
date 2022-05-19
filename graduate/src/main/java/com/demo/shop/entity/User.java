package com.demo.shop.entity;

import lombok.Data;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:31
 */
@Data
public class User {
    private String account;
    private String password;
    //账号类别 1 用户 2 商家 3 专家 4 管理员不能任意注册
    private String category;
}
