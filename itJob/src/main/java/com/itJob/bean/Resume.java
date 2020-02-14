package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 简历 类
 * @Author: LRJ
 * @Date: 2020/1/12 19:39
 */
@Data
@TableName("t_resume")
public class Resume {
    @TableId(value = "resume_id",type = IdType.UUID)
    String id;
    String applicantId;
    String position;
    String salary;
    String city;
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    Date date;
}
