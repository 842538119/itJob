package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Description: 企业 类
 * @Author: LRJ
 * @Date: 2019/12/27 18:03
 */
@Data
@TableName("t_enterprise")
public class Enterprise {
    @TableId(value = "enterprise_id",type = IdType.UUID)
    String id;
    String userId;
    @TableField(value ="enterprise_name")
    String name;
    String icon;
    String type;
    String financing;
    String phone;
    String email;
    String address;
    String introduction;

}
