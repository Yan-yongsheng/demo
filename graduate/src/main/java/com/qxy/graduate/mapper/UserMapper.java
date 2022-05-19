package com.qxy.graduate.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: yys
 * @Date: 2022/5/19 19:31
 */
@Mapper
public interface UserMapper {
    int register(String account, String password);

    int login(String account, String password);
}
