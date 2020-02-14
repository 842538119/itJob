package com.itJob.service;

import com.itJob.bean.Applicant;

import java.util.List;

/**
 * @Description: 求职者service 类
 * @Author: LRJ
 * @Date: 2019/12/27 16:12
 */
public interface TApplicantService {
    List<Applicant> getApplicantList(Integer PageNum, Integer PageSize);
    Applicant getOneById(String id);
    Applicant getOneByUserId(String userid);
    List<Applicant> getApplicantByCondition(Integer PageNum, Integer PageSize,Integer[] ages,Applicant applicant);
    int insert(Applicant applicant);
    int update(Applicant applicant);
    int delete(String id);
}
