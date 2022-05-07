package com.qxy.graduate.controller;

import com.qxy.graduate.common.ReturnData;
import com.qxy.graduate.entity.add.UserDemandAdd;
import com.qxy.graduate.service.IUserDemand;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demand")
public class UserDemandController {
    @Autowired
    private IUserDemand userDemandService;

    @PostMapping
    public ReturnData addUserDemand(@RequestBody UserDemandAdd userDemandAdd){
        System.out.println(userDemandAdd);
        return userDemandService.addUserDemand(userDemandAdd);
    }

    @GetMapping
    public ReturnData findUserDemandList(){
        return userDemandService.findUserDemandList();
    }

    @GetMapping("/{id}")
    public ReturnData findUserDemandById(@PathVariable("id") int id){
        return userDemandService.findUserDemandById(id);
    }

    @GetMapping("/match/{id}")
    public ReturnData getUserDemandResult(@PathVariable("id") int id){
        return userDemandService.getUserDemandResult(id);
    }

    @GetMapping("/limit/{id}")
    public ReturnData getUserLimitResult(@PathVariable("id") int id){
        return userDemandService.getUserLimitResult(id);
    }

    @GetMapping("/comment")
    public ReturnData submitComment(@RequestParam("goods_id") String id,
                                    @RequestParam("score") String score,
                                    @RequestParam("comment_details") String commentContent){
        return userDemandService.submitComment(id,score,commentContent);
    }
}
