package com.qxy.graduate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateTest {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test1(){
//        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        Map<String,String> paramMap=new HashMap<>();
        paramMap.put("goods_id","10901");
        paramMap.put("score","4");
        paramMap.put("comment_details","ok");
        ResponseEntity<JSONObject> entity = restTemplate.postForEntity("http://10.129.2.31:5000/add_comment", paramMap, JSONObject.class);
        System.out.println(entity.getBody().get("goods_id"));
    }

    @Test
    public void test2(){
        List<Integer> taskList=new ArrayList<>();
        List<Integer> limitList=new ArrayList<>();
        taskList.add(10);
        taskList.add(11);
        taskList.add(16);
        limitList.add(2400);
        limitList.add(20);
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("task_id_list",taskList);
        paramMap.put("user_qos_needed",limitList);
        ResponseEntity<JSONObject> entity = restTemplate.postForEntity("http://10.129.2.31:5555", paramMap, JSONObject.class);
        System.out.println(entity.getBody());
        Map data = entity.getBody().getObject("data",Map.class);
        List<List<Integer>> result = (List) data.get("result");
        System.out.println(result);
    }
}
