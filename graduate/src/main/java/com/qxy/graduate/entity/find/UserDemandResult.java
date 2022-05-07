package com.qxy.graduate.entity.find;

import com.qxy.graduate.entity.ServiceTotal;
import com.qxy.graduate.entity.UserDemand;
import lombok.Data;

import java.util.List;

@Data
public class UserDemandResult {
    private UserDemand userDemand;

    private List<List<ServiceTotal>> resultList;

    private int sum;

    private int groupSum;
}
