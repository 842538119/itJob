package com.itJob.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itJob.bean.Applicant;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 求职者mapper 类
 * @Author: LRJ
 * @Date: 2019/12/27 16:14
 */
@Mapper
public interface TApplicantMapper extends BaseMapper<Applicant> {
}
