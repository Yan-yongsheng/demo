package com.qxy.graduate.controller;

import com.alibaba.fastjson.JSON;
import com.qxy.graduate.common.ReturnData;
import com.qxy.graduate.entity.add.ServiceUploadAdd;
import com.qxy.graduate.service.IServiceUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceUpload")
public class ServiceUploadController {
    @Autowired
    private IServiceUpload serviceUpload;

    @PostMapping("/match")
    public ReturnData serviceMatch(@RequestBody String request){
        //requestbody传过来的数据是json格式的
        String words = JSON.parseObject(request).getObject("words", String.class);
        return serviceUpload.getMatchResult(words);
    }

    @PostMapping
    public ReturnData serviceUpload(@RequestBody ServiceUploadAdd serviceUploadAdd){
        // TODO: 2021/12/17 standard和equipment两个字段在其他地方没有用到
        return serviceUpload.serviceUpload(serviceUploadAdd);
    }

    @GetMapping
    public ReturnData getServiceUploadList(){
        return serviceUpload.getServiceUploadList();
    }
}
