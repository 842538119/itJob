package com.itJob.service;

import com.itJob.bean.PositionType;

import java.util.List;

/**
 * @Description: 岗位类别 service 类
 * @Author: LRJ
 * @Date: 2020/1/8 20:32
 */
public interface TPositionTypeService {
    List<PositionType> getPositionTypeList();
    PositionType getOneById(String id);
    int insert(PositionType positionType);
    int update(PositionType positionType);
    int delete(String id);
}
