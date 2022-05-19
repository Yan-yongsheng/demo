package com.qxy.graduate.service.impl;

import com.qxy.graduate.controller.UserController;
import com.qxy.graduate.entity.find.ServiceRate;
import com.qxy.graduate.mapper.OrderMapper;
import com.qxy.graduate.mapper.UserDemandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UpdateCommentService.class);
    @Autowired
    private OrderMapper OrderMapper;
    @Autowired
    private UserDemandMapper userDemandMapper;

//    @Scheduled(cron = "0 0 1 *  * ?")
    private void updateComment(){
        try{
            //update sql语句 使用加权将此服务的订单评分计算，更新到服务表中service_total
            List<ServiceRate> currentRates = OrderMapper.getOrderRate();
            for(ServiceRate serviceRate:currentRates){
                userDemandMapper.submitComment(serviceRate.getId(),serviceRate.getQualityScore(),serviceRate.getSpeedScore(),serviceRate.getAttitudeScore());
            }
            logger.info("更新评分成功！");
        }catch (Exception e){
           logger.error("更新评分失败",e);
        }


    }

}
