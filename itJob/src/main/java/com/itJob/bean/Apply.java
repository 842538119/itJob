package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 投递记录 类
 * @Author: LRJ
 * @Date: 2020/1/17 19:19
 */
@Data
@TableName("t_apply")
public class Apply {
    @TableId(value = "apply_id",type = IdType.UUID)
    String id;
    String applicantId;
    String positionId;
    String resumeId;
    Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    Date date;
}
