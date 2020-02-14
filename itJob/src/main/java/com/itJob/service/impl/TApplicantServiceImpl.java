package com.itJob.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itJob.bean.Applicant;
import com.itJob.bean.User;
import com.itJob.mapper.TApplicantMapper;
import com.itJob.service.TApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 求职者service实现 类
 * @Author: LRJ
 * @Date: 2019/12/27 16:13
 */
@Service
public class TApplicantServiceImpl implements TApplicantService {
    @Autowired
    private TApplicantMapper tApplicantMapper;
    @Override
    public List<Applicant> getApplicantList(Integer PageNum, Integer PageSize) {
        Page<Applicant> applicantPage=new Page<Applicant>(PageNum,PageSize);
        return tApplicantMapper.selectPage(applicantPage,null);
    }

    @Override
    public Applicant getOneById(String id) {
        return tApplicantMapper.selectById(id);
    }

    @Override
    public Applicant getOneByUserId(String userid) {
        EntityWrapper<Applicant> queryWrapper = new  EntityWrapper<Applicant>();
        List<Applicant> list=tApplicantMapper.selectList(queryWrapper.eq("user_id",userid));
        if(list.size()!=0){
            return list.get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public List<Applicant> getApplicantByCondition(Integer PageNum, Integer PageSize, Integer[] ages, Applicant applicant) {
        Page<Applicant> applicantPage=new Page<Applicant>(PageNum,PageSize);
        return null;
    }

    @Override
    public int insert(Applicant applicant) {
        return tApplicantMapper.insert(applicant);
    }

    @Override
    public int update(Applicant applicant) {
        EntityWrapper<Applicant> queryWrapper = new  EntityWrapper<Applicant>();
        queryWrapper.eq("applicant_id",applicant.getId());
        return tApplicantMapper.update(applicant,queryWrapper);
    }

    @Override
    public int delete(String id) { return tApplicantMapper.deleteById(id); }
}
