package com.itJob.bean.Vo;

import com.itJob.bean.*;
import lombok.Data;
import java.util.List;

/**
 * @Description: 简历展示 类
 * @Author: LRJ
 * @Date: 2020/1/12 21:08
 */
@Data
public class ResumeVo {
    Resume resume;
    Applicant applicant;
    List<ResumeEducation> resumeEducation;
    List<ResumeExperience> resumeExperiences;
    List<ResumeProject> resumeProjects;
}
