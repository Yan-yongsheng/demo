package com.demo.shop.service.impl;

import com.demo.shop.mapper.OrderMapper;
import com.demo.shop.service.SubmitCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmitCommentImpl implements SubmitCommentService {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public void submitComment(Integer id,List<String> serviceIdList, String userId, String qualityScore, String speedScore, String attitudeScore, String comment) {
        for(String serviceId :serviceIdList ){
            orderMapper.submitComment(id,serviceId,userId,qualityScore,speedScore,attitudeScore,comment);
        }

    }
}
