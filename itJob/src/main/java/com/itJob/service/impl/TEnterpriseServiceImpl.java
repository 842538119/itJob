package com.itJob.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itJob.bean.Enterprise;
import com.itJob.mapper.TEnterpriseMapper;
import com.itJob.service.TEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 企业service实现 类
 * @Author: LRJ
 * @Date: 2020/1/1 11:26
 */
@Service
public class TEnterpriseServiceImpl implements TEnterpriseService {

    @Autowired
    private TEnterpriseMapper tEnterpriseMapper;
    @Override
    public List<Enterprise> getEnterpriseList(Integer PageNum, Integer PageSize) {
        Page<Enterprise> EnterprisePage=new Page<Enterprise>(PageNum,PageSize);
        return tEnterpriseMapper.selectPage(EnterprisePage,null);
    }

    @Override
    public Enterprise getOneById(String id) {
        return tEnterpriseMapper.selectById(id);
    }

    @Override
    public Enterprise getOneByUserId(String userid) {
        EntityWrapper<Enterprise> queryWrapper = new  EntityWrapper<Enterprise>();
        List<Enterprise> list=tEnterpriseMapper.selectList(queryWrapper.eq("user_id",userid));
        if(list.size()!=0){
            return list.get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public List<Enterprise> getEnterpriseByCondition(Integer PageNum, Integer PageSize, Enterprise Enterprise) {
        Page<Enterprise> EnterprisePage=new Page<Enterprise>(PageNum,PageSize);
        return null;
    }

    @Override
    public int insert(Enterprise Enterprise) { return tEnterpriseMapper.insert(Enterprise); }

    @Override
    public int update(Enterprise enterprise) {
        EntityWrapper<Enterprise> queryWrapper = new  EntityWrapper<Enterprise>();
        queryWrapper.eq("enterprise_id",enterprise.getId());
        return tEnterpriseMapper.update(enterprise,queryWrapper); }

    @Override
    public int delete(String id) { return tEnterpriseMapper.deleteById(id); }
}
