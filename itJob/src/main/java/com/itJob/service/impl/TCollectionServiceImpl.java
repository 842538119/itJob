package com.itJob.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.itJob.bean.Collection;
import com.itJob.bean.Enterprise;
import com.itJob.bean.Po.PositionPo;
import com.itJob.bean.Position;
import com.itJob.bean.PositionType;
import com.itJob.bean.Vo.PositionVo;
import com.itJob.mapper.TCollectionMapper;
import com.itJob.service.TCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 收藏service实现 类
 * @Author: LRJ
 * @Date: 2020/1/23 20:28
 */
@Service
public class TCollectionServiceImpl implements TCollectionService {
    @Autowired
    private TCollectionMapper tCollectionMapper;
    @Override
    public List<PositionVo> getCollectionListByApplicantId(String applicantId) {
        List<PositionPo> list = tCollectionMapper.getPositionListByApplicantId(applicantId);
        List<PositionVo> list1=new ArrayList<PositionVo>();
        for(int i=0;i<list.size();i++){
            PositionVo positionVo=setPositionVo(list.get(i));
            list1.add(positionVo);
        }
        return list1;
    }

    @Override
    public Boolean isPositionCollected(Collection collection) {
        EntityWrapper<Collection> queryWrapper = new  EntityWrapper<Collection>();
        queryWrapper.eq("applicant_id",collection.getApplicantId());
        queryWrapper.eq("position_id",collection.getPositionId());
        List<Collection> list=tCollectionMapper.selectList(queryWrapper);
        if(list.size()==0){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public int insert(Collection collection) {
        return tCollectionMapper.insert(collection);
    }

    @Override
    public int delete(Collection collection) {
        EntityWrapper<Collection> queryWrapper = new  EntityWrapper<Collection>();
        queryWrapper.eq("applicant_id",collection.getApplicantId());
        queryWrapper.eq("position_id",collection.getPositionId());
        return tCollectionMapper.delete(queryWrapper);
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
}
