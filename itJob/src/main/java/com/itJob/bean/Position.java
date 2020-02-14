package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 职位 类
 * @Author: LRJ
 * @Date: 2020/1/2 21:19
 */
@Data
@TableName("t_position")
public class Position {
    @TableId(value = "position_id",type = IdType.UUID)
    String id;
    @TableField(value = "position_name")
    String name;
    String positionTypeId;
    String enterpriseId;
    String city;
    String salary;
    String education;
    String experience;
    String duties;
    @TableField(value = "position_status")
    Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @TableField(value = "position_date")
    Date date;
}
