package com.itJob.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itJob.bean.Apply;
import com.itJob.bean.Po.ApplyPo;
import com.itJob.bean.Po.PositionPo;
import com.itJob.bean.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 投递mapper 类
 * @Author: LRJ
 * @Date: 2020/1/17 20:07
 */
@Mapper
public interface TApplyMapper extends BaseMapper<Apply> {
    @Select("select ap.*,p.*,a.*,r.resume_id from t_apply ap,t_position p,t_resume r,t_applicant a " +
            "where p.enterprise_id=#{id, jdbcType=VARCHAR} and ap.status=#{status, jdbcType=INTEGER} and p.position_id=ap.position_id and " +
            "ap.applicant_id=a.applicant_id and ap.resume_id=r.resume_id")
    List<ApplyPo> getAppliesByEnterpriseId(@Param("id") String id, @Param("status")Integer status);

    @Select("select ap.*,p.*,e.* from t_apply ap,t_position p,t_enterprise e where " +
            "ap.applicant_id=#{id, jdbcType=VARCHAR} and ap.status=#{status, jdbcType=INTEGER} " +
            "and p.position_id=ap.position_id and e.enterprise_id=p.enterprise_id")
    List<PositionPo> getAppliesByApplicantId(@Param("id") String id, @Param("status")Integer status);
}
