package com.itJob.service;

import com.itJob.bean.Apply;
import com.itJob.bean.Po.ApplyPo;
import com.itJob.bean.Po.PositionPo;
import com.itJob.bean.Vo.ApplyVo;
import com.itJob.bean.Vo.PositionVo;

import java.util.List;

/**
 * @Description: 投递service 类
 * @Author: LRJ
 * @Date: 2020/1/17 20:09
 */
public interface TApplyService {
    List<PositionVo> getAppliesByApplicantId(String applicantId, Integer status);
    List<ApplyVo> getAppliesByEnterpriseId(String enterpriseId, Integer status);
    Boolean isPositionApplied(Apply apply);
    ApplyVo setApplyVo(ApplyPo applyPo);
    PositionVo setPositionVo(PositionPo positionPo);
    int insert(Apply apply);
    int update(Apply apply);
    int delete(Apply apply);
}
