package com.itJob.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itJob.bean.Po.PositionPo;
import com.itJob.bean.PositionType;
import com.itJob.bean.Enterprise;
import com.itJob.bean.Position;
import com.itJob.bean.Vo.PositionVo;
import com.itJob.mapper.TPositionMapper;
import com.itJob.service.TEnterpriseService;
import com.itJob.service.TPositionService;
import com.itJob.service.TPositionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 职位service实现 类
 * @Author: LRJ
 * @Date: 2020/1/2 21:41
 */
@Service
public class TPositionServiceImpl implements TPositionService {
    @Autowired
    private TPositionMapper tPositionMapper;
    @Autowired
    private TEnterpriseService tEnterpriseService;
    @Autowired
    private TPositionTypeService tPositionTypeService;
    @Override
    public List<PositionVo> getPositionList(Integer pageNum, Integer pageSize,String name,String city) {
        if(name.equals("")){
            name=null;
        }
        else{
            name="%"+name+"%";
        }
        if(city.equals("")){
            city=null;
        }
        Page<Position> positionPage = new Page<Position>(pageNum,pageSize);
        List<PositionPo> list = tPositionMapper.getPositionList(name,city,positionPage);
        List<PositionVo> list1=new ArrayList<PositionVo>();
        for(int i=0;i<list.size();i++) {
            PositionVo positionVo=setPositionVo(list.get(i));
            list1.add(positionVo);
        }
        return list1;
    }

    @Override
    public PositionVo getOneById(String id) {
        PositionPo positionPo=tPositionMapper.getOneById(id);
        PositionVo positionVo=setPositionVo(positionPo);
        return positionVo;
    }

    @Override
    public List<PositionVo> getPositionListByEnterpriseId(String enterpriseId) {
        EntityWrapper<Position> queryWrapper = new  EntityWrapper<Position>();
        List<PositionPo> list=tPositionMapper.getPositionListByEnterpriseId(enterpriseId);
        List<PositionVo> list1=new ArrayList<PositionVo>();
        for(int i=0;i<list.size();i++){
            PositionVo positionVo=setPositionVo(list.get(i));
            list1.add(positionVo);
        }
        return list1;
    }
    @Override
    public List<PositionVo> getPositionByCondition(Integer PageNum, Integer PageSize, String name,String city) {
        Page<Position> positionPage=new Page<Position>(PageNum,PageSize);
        EntityWrapper<Position> queryWrapper = new  EntityWrapper<Position>();
        //只查询已审核的职位
        queryWrapper.eq("position_status",0);
        queryWrapper.eq("city",city);
        //模糊查询名字
        queryWrapper.like("position_name",name);
        return null;
    }

    @Override
    public int insert(Position position) {
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Date date1 = null;
        try {
            date1 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        position.setDate(date1);
        return tPositionMapper.insert(position);
    }

    @Override
    public int update(Position position) {
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Date date1 = null;
        try {
            date1 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        position.setDate(date1);
        return tPositionMapper.updateById(position);
    }
    @Override
    public int delete(String id) { return tPositionMapper.deleteById(id); }

    @Override
    public PositionVo setPositionVo(PositionPo positionPo){
        PositionVo positionVo=new PositionVo();
        Position position=new Position();
        Enterprise enterprise=new Enterprise();
        PositionType positionType=new PositionType();
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
        positionType.setId(positionPo.getPositionTypeId());
        positionType.setName(positionPo.getPositionTypeName());
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
        positionVo.setPositionType(positionType);
        return  positionVo;
    }
}
