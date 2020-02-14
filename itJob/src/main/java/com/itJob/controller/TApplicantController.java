package com.itJob.controller;

import com.itJob.bean.Applicant;
import com.itJob.bean.Vo.Result;
import com.itJob.exception.ValidateException;
import com.itJob.service.TApplicantService;
import com.itJob.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 求职者controller 类
 * @Author: LRJ
 * @Date: 2019/12/27 16:12
 */
@RestController
@RequestMapping("/applicant")
public class TApplicantController {
    @Autowired
    private TApplicantService tApplicantService;
    @RequestMapping("/getOneByUserId")
    public Result getOneByUserId(String userid){
            Result result=new Result();
            Applicant applicant=tApplicantService.getOneByUserId(userid);
            if(applicant!=null){
                result.setSuccess(true);
                result.setMessage("获取个人信息成功");
                result.setData(applicant);
            }
            else{
                result.setSuccess(false);
                result.setMessage("获取个人信息失败");
            }
             return result;
    }
    @RequestMapping("/updateApplicant")
    public Result updateApplicant(@RequestBody Applicant applicant){
        Result result=new Result();
        int count= tApplicantService.update(applicant);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("修改个人信息成功");
        }
        else{
           result.setSuccess(false);
           result.setMessage("修改个人信息失败");
        }
        return result;
    }
}
