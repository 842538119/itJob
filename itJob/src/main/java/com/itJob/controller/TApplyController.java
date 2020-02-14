package com.itJob.controller;


import com.itJob.bean.Apply;
import com.itJob.bean.Vo.ApplyVo;
import com.itJob.bean.Vo.PositionVo;
import com.itJob.bean.Vo.Result;
import com.itJob.service.TApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 投递Controller 类
 * @Author: LRJ
 * @Date: 2020/1/17 23:18
 */
@RestController
@RequestMapping("/apply")
public class TApplyController {
    @Autowired
    private TApplyService tApplyService;
    /**
     * @Description: 求职者获得投递列表方法
     * @param: * @Param applicantId:
     * @Param status:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/18 20:22
     */
    @RequestMapping("/getAppliesByApplicantId")
    public Result getAppliesByApplicantId(String applicantId, Integer status){
        Result result=new Result();
        List<PositionVo> list=tApplyService.getAppliesByApplicantId(applicantId,status);
        result.setSuccess(true);
        result.setData(list);
        return result;
    }
    /**
     * @Description: 企业获得投递列表方法
     * @param: * @Param enterpriseId:
     * @Param status:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/18 20:23
     */
    @RequestMapping("/getAppliesByEnterpriseId")
    public Result getAppliesByEnterpriseId(String enterpriseId, Integer status){
        Result result=new Result();
        List<ApplyVo> list=tApplyService.getAppliesByEnterpriseId(enterpriseId,status);
        result.setSuccess(true);
        result.setData(list);
        return result;
    }
    /**
     * @Description: 职位是否投递方法
     * @param: * @Param apply: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/26 23:46
     */
    @RequestMapping("/isApplied")
    public Result isApplied(Apply apply){
        Result result=new Result();
        if(tApplyService.isPositionApplied(apply)){
            result.setData("true");
        }
        else{
            result.setData("false");
        }
        result.setSuccess(true);
        return result;
    }
    /**
     * @Description: 插入投递信息方法
     * @param: * @Param apply: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/26 23:24
     */
    @RequestMapping(value = "/insertApply",method = RequestMethod.POST)
    public Result insertApply(@RequestBody  Apply apply){
        Result result = new Result();
        int count=tApplyService.insert(apply);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("投递成功!");
        }
        else{
            result.setSuccess(false);
            result.setMessage("投递失败!");
        }
        return result;
    }
    /**
     * @Description: 更新投递信息方法
     * @param: * @Param apply: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/26 23:29
     */
    @RequestMapping(value = "/updateApply",method = RequestMethod.PUT)
    public Result updateApply(@RequestBody  Apply apply){
        Result result = new Result();
        int count=tApplyService.update(apply);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("处理信息成功!");
        }
        else{
            result.setSuccess(false);
            result.setMessage("处理信息失败!");
        }
        return result;
    }
}
