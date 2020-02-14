package com.itJob.controller;

import com.itJob.bean.Enterprise;
import com.itJob.bean.Position;
import com.itJob.bean.Vo.PositionVo;
import com.itJob.bean.Vo.Result;
import com.itJob.service.TEnterpriseService;
import com.itJob.service.TPositionService;
import com.itJob.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 企业controller 类
 * @Author: LRJ
 * @Date: 2020/1/1 11:31
 */
@RestController
@RequestMapping("/enterprise")
public class TEnterpriseController {
    @Autowired
    private TEnterpriseService tEnterpriseService;
    @Autowired
    private TPositionService tPositionService;
    @RequestMapping("/getOneById")
    public Result getOneById(String id){
        Result result=new Result();
        Enterprise enterprise=tEnterpriseService.getOneById(id);
        if(enterprise!=null){
            result.setSuccess(true);
            result.setMessage("获取企业信息成功");
            result.setData(enterprise);
        }
        else{
            result.setSuccess(false);
            result.setMessage("获取企业信息失败");
        }
        return result;
    }
    @RequestMapping("/getOneByUserId")
    public Result getOneByUserId(String userid){
        Result result=new Result();
        Enterprise enterprise=tEnterpriseService.getOneByUserId(userid);
        if(enterprise!=null){
            result.setSuccess(true);
            result.setMessage("获取企业信息成功");
            result.setData(enterprise);
        }
        else{
            result.setSuccess(false);
            result.setMessage("获取企业信息失败");
        }
        return result;
    }
    @RequestMapping("/insertEnterprise")
    public Result insertEnterprise(@RequestBody Enterprise enterprise){
        Result result=new Result();
        int count= tEnterpriseService.insert(enterprise);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("提交成功，请待管理员审核！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("提交失败，请重试！");
        }
        return result;
    }
    @RequestMapping("/updateEnterprise")
    public Result updateEnterprise(@RequestBody Enterprise enterprise){
        Result result=new Result();
        int count= tEnterpriseService.update(enterprise);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("修改企业信息成功");
        }
        else{
            result.setSuccess(false);
            result.setMessage("修改企业信息失败");
        }
        return result;
    }
}
