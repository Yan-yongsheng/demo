package com.qxy.graduate.service.impl;

import com.qxy.graduate.entity.find.ServiceRate;
import com.qxy.graduate.mapper.UserDemandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yys
 * @Date: 2022/5/12 18:57
 */
@Service
public class UpdateCommentService {
    @Autowired
    private UserDemandMapper userDemandMapper;

//    @Scheduled(cron = "0 0 1 *  * ?")
    private void updateComment(){
        try{
            //缺少订单表
            //update sql语句 使用加权将此服务的订单评分计算，更新到服务表中service_total
            List<ServiceRate> currentRates = userDemandMapper.getOrderRate();
            for(ServiceRate serviceRate:currentRates){
                userDemandMapper.submitComment(serviceRate.getId(),serviceRate.getQualityScore(),serviceRate.getSpeedScore(),serviceRate.getAttitudeScore());
            }
        }catch (Exception e){
           //
        }


    }

}
