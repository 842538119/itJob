package com.itJob.service;

import com.itJob.bean.Resume;
import com.itJob.bean.ResumeEducation;
import com.itJob.bean.ResumeExperience;
import com.itJob.bean.ResumeProject;
import com.itJob.bean.Vo.ResumeVo;

import java.util.List;

/**
 * @Description: 简历service 类
 * @Author: LRJ
 * @Date: 2020/1/12 21:05
 */
public interface TResumeService {
    ResumeVo getOneById(String id);
    ResumeEducation getResumeEducationById(String id);
    ResumeExperience getResumeExperienceById(String id);
    ResumeProject getResumeProjectById(String id);
    List<Resume> getResumeByApplicantId(String applicantId);
    List<ResumeEducation> getResumeEducationByResumeId(String resumeId);
    List<ResumeExperience> getResumeExperienceByResumeId(String resumeId);
    List<ResumeProject> getResumeProjectByResumeId(String resumeId);
    int insertResume(Resume resume);
    int insertResumeEducation(ResumeEducation resumeEducation);
    int insertResumeExperience(ResumeExperience resumeExperience);
    int insertResumeProject(ResumeProject resumeProject);
    int updateResume(Resume resume);
    int updateResumeEducation(ResumeEducation resumeEducation);
    int updateResumeExperience(ResumeExperience resumeExperience);
    int updateResumeProject(ResumeProject resumeProject);
    int deleteResume(String id);
    int deleteResumeEducation(String id);
    int deleteResumeExperience(String id);
    int deleteResumeProject(String id);
}
