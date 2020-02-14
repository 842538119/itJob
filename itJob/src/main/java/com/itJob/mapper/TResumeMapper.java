package com.itJob.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itJob.bean.Resume;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 简历mapper 类
 * @Author: LRJ
 * @Date: 2020/1/12 21:02
 */
@Mapper
public interface TResumeMapper extends BaseMapper<Resume> {
}
