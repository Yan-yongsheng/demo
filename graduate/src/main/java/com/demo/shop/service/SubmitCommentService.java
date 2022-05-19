package com.demo.shop.service;

import java.util.List;

public interface SubmitCommentService {
    void submitComment(Integer id,List<String> serviceIdList,String userId,
                       String qualityScore,String speedScore,String attitudeScore,String comment);
}
