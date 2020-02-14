package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Description: 用户 类
 * @Author: LRJ
 * @Date: 2019/11/27 14:36
 */
@Data
@TableName("t_user")
public class User {
    @TableId(value = "id",type = IdType.UUID)
    String id;
    String openid;
    String username;
    String password;
    String type;
    Integer status;
}
