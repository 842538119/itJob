package com.itJob.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itJob.bean.PositionType;
import com.itJob.bean.ResumeProject;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 简历项目经历mapper 类
 * @Author: LRJ
 * @Date: 2020/1/12 21:04
 */
@Mapper
public interface TResumeProjectMapper extends BaseMapper<ResumeProject> {
}
