package com.itJob.service;

import com.itJob.bean.User;

import java.util.List;

/**
 * @Description: 用户service 类
 * @Author: LRJ
 * @Date: 2019/11/28 13:58
 */
public interface TUserService {
    User registerByOpenId(String openid,String name,String sex,String icon,String type);
    void register(User user);
    User login(User user);
    List<User> getUserList(Integer PageNum,Integer PageSize);
    User getOneById(String id);
    User getOneByOpenId(String openid);
    User getOneByUserName(String username);
    List<User> getUserByType(String type);
    List<User> getUserByStatus(Integer status);
    int insert(User user);
    int update(User user);
    int delete(String id);
}
