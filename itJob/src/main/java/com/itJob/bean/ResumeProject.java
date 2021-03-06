package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 简历项目经历 类
 * @Author: LRJ
 * @Date: 2020/1/12 20:57
 */
@Data
@TableName("t_resume_project")
public class ResumeProject {
    @TableId(value = "id",type = IdType.UUID)
    String id;
    String resumeId;
    String name;
    String personJob;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    Date endTime;
    String content;
}
