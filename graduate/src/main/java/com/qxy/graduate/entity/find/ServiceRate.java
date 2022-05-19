package com.qxy.graduate.entity.find;

import lombok.Data;

/**
 * @Author: yys
 * @Date: 2022/5/12 19:38
 */

@Data
public class ServiceRate {
    //服务id
    private Integer id;
    //质量评分
    private Integer qualityScore;

    //速度评分
    private Integer speedScore;

    //态度评分
    private Integer attitudeScore;
}
