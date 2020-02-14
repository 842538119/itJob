package com.itJob.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itJob.bean.Collection;
import com.itJob.bean.Po.PositionPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 收藏mapper 类
 * @Author: LRJ
 * @Date: 2020/1/23 20:27
 */
@Mapper
public interface TCollectionMapper extends BaseMapper<Collection> {
    @Select("select * from t_position p,t_collection c,t_enterprise e where " +
            "c.applicant_id=#{id, jdbcType=VARCHAR}  and " +
            "c.position_id=p.position_id and e.enterprise_id=p.enterprise_id")
    List<PositionPo> getPositionListByApplicantId(String id);
}
