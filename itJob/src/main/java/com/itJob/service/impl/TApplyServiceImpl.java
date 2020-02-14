package com.itJob.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.itJob.bean.*;
import com.itJob.bean.Po.ApplyPo;
import com.itJob.bean.Po.PositionPo;
import com.itJob.bean.Vo.ApplyVo;
import com.itJob.bean.Vo.PositionVo;
import com.itJob.mapper.TApplyMapper;
import com.itJob.service.TApplyService;
import com.itJob.service.TPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 投递service实现 类
 * @Author: LRJ
 * @Date: 2020/1/17 20:10
 */
@Service
public class TApplyServiceImpl implements TApplyService {
    @Autowired
    private TApplyMapper tApplyMapper;
    @Autowired
    private TPositionService tPositionService;
    /**
     * @Description: 获得用户投递列表方法
     * @param: * @Param applicantId:
     * @Param status:
     * @return: java.util.List<com.itJob.bean.Vo.PositionVo>
     * @auther: LRJ
     * @date: 2020/1/18 19:21
     */
    @Override
    public List<PositionVo> getAppliesByApplicantId(String applicantId, Integer status) {
        List<PositionVo> positionVos=new ArrayList<PositionVo>();
        List<PositionPo> list=tApplyMapper.getAppliesByApplicantId(applicantId,status);
        for(int i=0;i<list.size();i++){
            PositionVo positionVo=setPositionVo(list.get(i));
            positionVos.add(positionVo);
        }
        return positionVos;
    }

    @Override
    public List<ApplyVo> getAppliesByEnterpriseId(String enterpriseId, Integer status) {
        List<ApplyVo> applyVos=new ArrayList<ApplyVo>();
        List<ApplyPo> list=tApplyMapper.getAppliesByEnterpriseId(enterpriseId,status);
        for(int i=0;i<list.size();i++){
            ApplyVo applyVo=setApplyVo(list.get(i));
            applyVos.add(applyVo);
        }
        return applyVos;
    }

    @Override
    public Boolean isPositionApplied(Apply apply) {
        EntityWrapper<Apply> queryWrapper = new  EntityWrapper<Apply>();
        queryWrapper.eq("applicant_id",apply.getApplicantId());
        queryWrapper.eq("position_id",apply.getPositionId());
        List<Apply> list=tApplyMapper.selectList(queryWrapper);
        if(list.size()==0){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public ApplyVo setApplyVo(ApplyPo applyPo) {
        ApplyVo applyVo=new ApplyVo();
        Apply apply=new Apply();
        Applicant applicant=new Applicant();
        Position position=new Position();
        Resume resume=new Resume();
        apply.setId(applyPo.getApplyId());
        apply.setStatus(applyPo.getStatus());
        apply.setDate(applyPo.getDate());
        applicant.setId(applyPo.getApplicantId());
        applicant.setName(applyPo.getApplicantName());
        applicant.setIcon(applyPo.getIcon());
        applicant.setSex(applyPo.getSex());
        applicant.setAge(applyPo.getAge());
        applicant.setCity(applyPo.getCity());
        applicant.setEducation(applyPo.getEducation());
        position.setId(applyPo.getPositionId());
        position.setName(applyPo.getPositionName());
        resume.setId(applyPo.getResumeId());
        applyVo.setApply(apply);
        applyVo.setApplicant(applicant);
        applyVo.setPosition(position);
        applyVo.setResume(resume);
        return applyVo;
    }

    @Override
    public PositionVo setPositionVo(PositionPo positionPo){
        PositionVo positionVo=new PositionVo();
        Position position=new Position();
        Enterprise enterprise=new Enterprise();
        position.setId(positionPo.getPositionId());
        position.setEnterpriseId(positionPo.getEnterpriseId());
        position.setPositionTypeId(positionPo.getPositionTypeId());
        position.setName(positionPo.getPositionName());
        position.setCity(positionPo.getCity());
        position.setSalary(positionPo.getSalary());
        position.setExperience(positionPo.getExperience());
        position.setEducation(positionPo.getEducation());
        position.setDuties(positionPo.getDuties());
        position.setStatus(positionPo.getPositionStatus());
        position.setDate(positionPo.getPositionDate());
        enterprise.setId(positionPo.getEnterpriseId());
        enterprise.setName(positionPo.getEnterpriseName());
        enterprise.setIcon(positionPo.getIcon());
        enterprise.setPhone(positionPo.getPhone());
        enterprise.setFinancing(positionPo.getFinancing());
        enterprise.setType(positionPo.getType());
        enterprise.setEmail(positionPo.getEmail());
        enterprise.setAddress(positionPo.getAddress());
        enterprise.setIntroduction(positionPo.getIntroduction());
        positionVo.setEnterprise(enterprise);
        positionVo.setPosition(position);
        return  positionVo;
    }
    @Override
    public int insert(Apply apply) {
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Date date1 = null;
        try {
            date1 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        apply.setDate(date1);
        apply.setStatus(0);
        return tApplyMapper.insert(apply);
    }

    @Override
    public int update(Apply apply) {
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Date date1 = null;
        try {
            date1 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        apply.setDate(date1);
        return tApplyMapper.updateById(apply);
    }

    @Override
    public int delete(Apply apply) {
        return 0;
    }
}
