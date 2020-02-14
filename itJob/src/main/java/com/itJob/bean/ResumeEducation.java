package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 简历教育经历 类
 * @Author: LRJ
 * @Date: 2020/1/12 20:56
 */
@Data
@TableName("t_resume_education")
public class ResumeEducation {
    @TableId(value = "id",type = IdType.UUID)
    String id;
    String resumeId;
    String school;
    String education;
    String profession;
    String startYear;
    String endYear;
}
