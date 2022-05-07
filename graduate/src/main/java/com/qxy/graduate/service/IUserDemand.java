package com.qxy.graduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qxy.graduate.common.ReturnData;
import com.qxy.graduate.entity.UserDemand;
import com.qxy.graduate.entity.add.UserDemandAdd;
import com.qxy.graduate.entity.find.UserDemandResult;

import java.util.List;

public interface IUserDemand extends IService<UserDemand> {
    ReturnData<String> addUserDemand(UserDemandAdd userDemandAdd);

    ReturnData<List<UserDemand>> findUserDemandList();

    ReturnData<UserDemand> findUserDemandById(int id);

    ReturnData<UserDemandResult> getUserDemandResult(int id);

    ReturnData<UserDemandResult> getUserLimitResult(int id);

    ReturnData<String> submitComment(String id,String score,String commentContent);
}
