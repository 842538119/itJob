package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Description: 求职者 类
 * @Author: LRJ
 * @Date: 2019/12/27 16:09
 */
@Data
@TableName("t_applicant")
public class Applicant {
    @TableId(value = "applicant_id",type = IdType.UUID)
    String id;
    String userId;
    @TableField(value ="applicant_name")
    String name;
    String sex;
    Integer age;
    String icon;
    String education;
    String phone;
    String city;
    String address;
    String email;
    String introduction;
}
