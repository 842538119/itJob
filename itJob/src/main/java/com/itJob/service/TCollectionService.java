package com.itJob.service;

import com.itJob.bean.Collection;
import com.itJob.bean.Po.PositionPo;
import com.itJob.bean.Vo.PositionVo;

import java.util.List;

/**
 * @Description: 收藏service 类
 * @Author: LRJ
 * @Date: 2020/1/23 20:28
 */
public interface TCollectionService {
    List<PositionVo> getCollectionListByApplicantId(String applicantId);
    Boolean isPositionCollected(Collection collection);
    int insert(Collection collection);
    int delete(Collection collection);
    PositionVo setPositionVo(PositionPo positionPo);
}
