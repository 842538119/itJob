package com.itJob.controller;

import com.alibaba.fastjson.JSONObject;
import com.itJob.bean.Applicant;
import com.itJob.bean.User;
import com.itJob.bean.Vo.Result;
import com.itJob.exception.ValidateException;
import com.itJob.service.TApplicantService;
import com.itJob.service.TUserService;
import com.itJob.util.wechat.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 用户Controller 类
 * @Author: LRJ
 * @Date: 2019/11/28 14:05
 */
@RestController
@RequestMapping("/user")
public class TUserController {
    @Autowired
    TUserService tUserService;
    @Autowired
    TApplicantService tApplicantService;
    @RequestMapping("/register")
    public Result register(User user){
        Result result=new Result();
        try{
            tUserService.register(user);
            result.setSuccess(true);
            result.setMessage("注册成功");
            result.setData(user);

        }catch (ValidateException e){
            result.setSuccess(false);
            result.setMessage(e.getExceptionEnums().getEmsg());
        }
        return result;
    }
    @RequestMapping("/loginByPassword")
    public Result loginByPassword(User user){
        Result result=new Result();
        try{
            User data=tUserService.login(user);
            result.setSuccess(true);
            result.setMessage("登录成功");
            result.setData(data);

        }catch (ValidateException e){
            result.setSuccess(false);
            result.setMessage(e.getExceptionEnums().getEmsg());
        }
        return result;
    }
    @RequestMapping(value = "/loginByWeChat", method = RequestMethod.GET)
    public Result loginByWechat(String code,String name,String sex,String icon,String type) {
        Result result=new Result();
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        try {
            if (code==null || code.equals("")) {
                result.setSuccess(false);
                result.setMessage("凭证有误，授权登录失败");
            } else {
                String requestUrl = WX_URL.replace("APPID", "wx8cbc9d1d5ebf539f").replace("SECRET", "5c2170be0dec235a26246709703c90f5")
                        .replace("JSCODE", code).replace("authorization_code", "authorization_code");
                JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
                if (jsonObject != null) {
                    try {
                        // 业务操作
                        String openid = jsonObject.getString("openid");
                        User user=tUserService.getOneByOpenId(openid);
                        //如果第一次授权登录，插入新用户和个人信息数据
                        if(user==null){
                          user=tUserService.registerByOpenId(openid,name,sex,icon,type);
                        }
                        result.setSuccess(true);
                        result.setMessage("授权登录成功");
                        result.setData(user);
                    } catch (Exception e) {
                        result.setSuccess(false);
                        result.setMessage("授权登录失败");
                    }
                }
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("凭证有误，授权登录失败");
        }
        return result;
    }
}
