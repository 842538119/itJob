package com.itJob.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.itJob.bean.Po.PositionPo;
import com.itJob.bean.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description: 职位mapper 类
 * @Author: LRJ
 * @Date: 2020/1/2 21:31
 */
@Mapper
public interface TPositionMapper extends BaseMapper<Position> {
    @Select("select * from t_position p,t_enterprise e,t_position_type pt where " +
            "p.enterprise_id=#{id, jdbcType=VARCHAR} and p.enterprise_id=e.enterprise_id and " +
            "p.position_type_id=pt.position_type_id")
    List<PositionPo> getPositionListByEnterpriseId(String id);

    @Select("select * from t_position p,t_enterprise e,t_position_type pt where " +
            "p.position_id=#{id, jdbcType=VARCHAR} and p.enterprise_id=e.enterprise_id and " +
            "p.position_type_id=pt.position_type_id")
    PositionPo getOneById(String id);

    @Select({"<script>",
            "select * from t_position p,t_enterprise e,t_position_type pt",
            "where p.enterprise_id=e.enterprise_id and p.position_type_id=pt.position_type_id",
            "<when test='name!=null'>",
            "and p.position_name like #{name, jdbcType=VARCHAR}",
            "</when>",
            "<when test='city!=null'>",
            "and p.city=#{city, jdbcType=VARCHAR} ",
            "</when>",
            "</script>"})
    List<PositionPo> getPositionList(@Param("name") String name,@Param("city") String city, Page<Position> page);
}
