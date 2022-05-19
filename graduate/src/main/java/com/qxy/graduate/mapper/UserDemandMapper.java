package com.qxy.graduate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qxy.graduate.entity.UserDemand;
import com.qxy.graduate.entity.find.ServiceRate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDemandMapper extends BaseMapper<UserDemand> {
    void submitComment(@Param("id") int id, @Param("qualityScore") int qualityScore,
                       @Param("speedScore") int speedScore, @Param("attitudeScore") int attitudeScore);

}
