package com.itJob.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itJob.bean.Applicant;
import com.itJob.bean.Enterprise;
import com.itJob.bean.User;
import com.itJob.exception.ValidateException;
import com.itJob.exception.ValidateExceptionEnums;
import com.itJob.mapper.TUserMapper;
import com.itJob.service.TApplicantService;
import com.itJob.service.TEnterpriseService;
import com.itJob.service.TUserService;
import com.itJob.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 用户service实现 类
 * @Author: LRJ
 * @Date: 2019/11/28 14:00
 */
@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    TUserMapper tUserMapper;
    @Autowired
    TApplicantService tApplicantService;
    @Autowired
    TEnterpriseService tEnterpriseService;
    @Override
    public void register(User user) {
        EntityWrapper<User> queryWrapper = new  EntityWrapper<User>();
        //账号未存在时才可注册
        if(tUserMapper.selectList(queryWrapper.eq("username",user.getUsername())).size()==0){
            String encryptedPwd = MD5Util.getEncryptedPwd(user.getPassword());
            user.setPassword(encryptedPwd);
            //判断账号类型
            if(user.getType().equals("求职者")){
                //求职者注册无需审核
                user.setStatus(1);
                insert(user);
                //插入新数据到求职者表
                Applicant applicant=new Applicant();
                applicant.setName(user.getUsername());
                applicant.setUserId(user.getId());
                tApplicantService.insert(applicant);
            }
            else if(user.getType().equals("企业")){
                //企业注册需审核
                user.setStatus(0);
                insert(user);
            }
        }
        else{
            //账号已存在抛出异常
            throw new ValidateException(ValidateExceptionEnums.USER_HAS_EXIST );
        }
    }
    @Override
    public User registerByOpenId(String openid,String name,String sex,String icon,String type){
        User user=new User();
        user.setOpenid(openid);
        user.setType(type);
        //判断账号类型
        if(type.equals("求职者")){
            //求职者注册无需审核
            user.setStatus(1);
            insert(user);
            //插入新数据到求职者表
            Applicant applicant=new Applicant();
            applicant.setUserId(user.getId());
            applicant.setName(name);
            if(sex.equals("1")){
                applicant.setSex("男");
            }
            else if(sex.equals("2")){
                applicant.setSex("女");
            }
            applicant.setIcon(icon);
            tApplicantService.insert(applicant);
        }
        else if(type.equals("企业")){
            //企业注册需审核
            user.setStatus(0);
            insert(user);
        }
        return user;
    }
    @Override
    public User login(User user) {
        EntityWrapper<User> queryWrapper = new  EntityWrapper<User>();
        List<User> list=tUserMapper.selectList(queryWrapper.eq("username",user.getUsername()));
       //账号不存在
       if(list.size()==0){
           throw new ValidateException(ValidateExceptionEnums.USER_HAS_NO_EXIST );
       }
       User user1=list.get(0);
       //账号被冻结
       if(user1!=null && user1.getStatus()==2){
           throw new ValidateException(ValidateExceptionEnums.USER_IS_DISABLED);
        }
        //账号未审核
        if(user1!=null && user1.getStatus()==0){
            throw new ValidateException(ValidateExceptionEnums.USER_IS_NO_PASSED);
        }
       //账号类型错误
       if(user1!=null && !user.getType().equals(user1.getType())){
          throw new ValidateException(ValidateExceptionEnums.USER_TYPE_IS_INCORRECT);
       }
       //进行密码校验
         String pwdInDb=user1.getPassword();
         if(!MD5Util.validPassword(user.getPassword(), pwdInDb)){
             throw new ValidateException(ValidateExceptionEnums.PASSWORD_IS_INCORRECT);
         }
         else{
             user.setId(user1.getId());
             user.setStatus(user1.getStatus());
            return user;
         }
    }
    @Override
    public List<User> getUserList(Integer PageNum,Integer PageSize) {
        Page<User> userPage=new Page<User>(PageNum,PageSize);
        return tUserMapper.selectPage(userPage,null);
    }
    @Override
    public User getOneById(String id) {
        return tUserMapper.selectById(id);
    }
    @Override
    public User getOneByOpenId(String openid) {
        EntityWrapper<User> queryWrapper = new  EntityWrapper<User>();
        List<User> list=tUserMapper.selectList(queryWrapper.eq("openid",openid));
        if(list.size()==0){
            return null;
        }
        else{
            return list.get(0);
        }
    }
    @Override
    public User getOneByUserName(String username) {
        EntityWrapper<User> queryWrapper = new  EntityWrapper<User>();
        return tUserMapper.selectList(queryWrapper.eq("username",username)).get(0);
    }
    @Override
    public List<User> getUserByType(String type) {
        EntityWrapper<User> queryWrapper = new  EntityWrapper<User>();
        return tUserMapper.selectList(queryWrapper.eq("type",type));
    }
    @Override
    public List<User> getUserByStatus(Integer status) {
        EntityWrapper<User> queryWrapper = new  EntityWrapper<User>();
        return tUserMapper.selectList(queryWrapper.eq("status",status));
    }
    @Override
    public int insert(User user) {
        return tUserMapper.insert(user);
    }
    @Override
    public int update(User user) {
        return tUserMapper.updateById(user);
    }
    @Override
    public int delete(String id) {
        return tUserMapper.deleteById(id);
    }
}
