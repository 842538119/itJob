package com.itJob.service;

import com.itJob.bean.Po.PositionPo;
import com.itJob.bean.Position;
import com.itJob.bean.Vo.PositionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 职位service 类
 * @Author: LRJ
 * @Date: 2020/1/2 21:32
 */
@Service
public interface TPositionService {
    List<PositionVo> getPositionList(Integer PageNum, Integer PageSize,String name,String city);
    PositionVo getOneById(String id);
    List<PositionVo> getPositionListByEnterpriseId(String enterpriseId);
    List<PositionVo> getPositionByCondition(Integer PageNum, Integer PageSize, String name,String city);
    int insert(Position position);
    int update(Position position);
    int delete(String id);
    PositionVo setPositionVo(PositionPo positionPo);
}
