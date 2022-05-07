package com.qxy.graduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qxy.graduate.common.ReturnData;
import com.qxy.graduate.entity.ServiceUpload;
import com.qxy.graduate.entity.add.ServiceUploadAdd;
import com.qxy.graduate.entity.find.MatchResult;

import java.util.List;

public interface IServiceUpload extends IService<ServiceUpload> {
    ReturnData<MatchResult> getMatchResult(String words);
    ReturnData<String> serviceUpload(ServiceUploadAdd serviceUploadAdd);
    ReturnData<List<ServiceUpload>> getServiceUploadList();
}
