package com.itJob.service;

import com.itJob.bean.Enterprise;

import java.util.List;

/**
 * @Description: 企业service 类
 * @Author: LRJ
 * @Date: 2020/1/1 11:25
 */
public interface TEnterpriseService {
    List<Enterprise> getEnterpriseList(Integer PageNum, Integer PageSize);
    Enterprise getOneById(String id);
    Enterprise getOneByUserId(String userid);
    List<Enterprise> getEnterpriseByCondition(Integer PageNum, Integer PageSize,Enterprise Enterprise);
    int insert(Enterprise Enterprise);
    int update(Enterprise Enterprise);
    int delete(String id);
}
