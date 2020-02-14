package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Description: 岗位类别 类
 * @Author: LRJ
 * @Date: 2020/1/8 20:25
 */
@Data
@TableName("t_position_type")
public class PositionType {
    @TableId(value = "position_type_id",type = IdType.UUID)
    String id;
    @TableField(value = "position_type_name")
    String name;
}
