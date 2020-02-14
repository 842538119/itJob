package com.itJob.service.impl;

import com.itJob.bean.PositionType;
import com.itJob.mapper.TPositionTypeMapper;
import com.itJob.service.TPositionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 岗位类别 service实现 类
 * @Author: LRJ
 * @Date: 2020/1/8 20:34
 */
@Service
public class TPositionTypeServiceImpl implements TPositionTypeService {
    @Autowired
    private TPositionTypeMapper tPositionTypeMapper;
    @Override
    public List<PositionType> getPositionTypeList() {
        return tPositionTypeMapper.selectList(null);
    }

    @Override
    public PositionType getOneById(String id) {
        return tPositionTypeMapper.selectById(id);
    }

    @Override
    public int insert(PositionType positionType) {
        return tPositionTypeMapper.insert(positionType);
    }

    @Override
    public int update(PositionType positionType) {
        return tPositionTypeMapper.updateById(positionType);
    }

    @Override
    public int delete(String id) {
        return tPositionTypeMapper.deleteById(id);
    }
}
