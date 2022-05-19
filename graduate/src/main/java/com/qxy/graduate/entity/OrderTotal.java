package com.qxy.graduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: yys
 * @Date: 2022/5/19 21:12
 */
@Data
@TableName("order_total")
public class OrderTotal implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    private String serviceId;

    private String serviceDetail;

    private String organizationId;

    private String userId;

    private String price;

    private String cycle;

    private String serviceScore;

    private String qualityScore;

    private String speedScore;

    private String attitudeScore;

    private Date createTime;

    private Date completeTime;

    private String comment;
}
