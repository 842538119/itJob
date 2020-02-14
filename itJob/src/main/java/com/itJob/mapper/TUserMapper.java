package com.itJob.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itJob.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 用户mapper 类
 * @Author: LRJ
 * @Date: 2019/11/27 14:40
 */
@Mapper
public interface TUserMapper extends BaseMapper<User> {
}
