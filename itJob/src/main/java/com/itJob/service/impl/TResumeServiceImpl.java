package com.itJob.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.itJob.bean.Resume;
import com.itJob.bean.ResumeEducation;
import com.itJob.bean.ResumeExperience;
import com.itJob.bean.ResumeProject;
import com.itJob.bean.Vo.ResumeVo;
import com.itJob.mapper.TResumeEducationMapper;
import com.itJob.mapper.TResumeExperienceMapper;
import com.itJob.mapper.TResumeMapper;
import com.itJob.mapper.TResumeProjectMapper;
import com.itJob.service.TApplicantService;
import com.itJob.service.TResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 简历service实现 类
 * @Author: LRJ
 * @Date: 2020/1/12 21:05
 */
@Service
public class TResumeServiceImpl implements TResumeService {
    @Autowired
    private TApplicantService tApplicantService;
    @Autowired
    private TResumeMapper tResumeMapper;
    @Autowired
    private TResumeEducationMapper tResumeEducationMapper;
    @Autowired
    private TResumeExperienceMapper tResumeExperienceMapper;
    @Autowired
    private TResumeProjectMapper tResumeProjectMapper;

    @Override
    public ResumeVo getOneById(String id) {
        ResumeVo resumeVo=new ResumeVo();
        Resume resume=new Resume();
        resume=tResumeMapper.selectById(id);
        resumeVo.setApplicant(tApplicantService.getOneById(resume.getApplicantId()));
        resumeVo.setResume(resume);
        resumeVo.setResumeEducation(getResumeEducationByResumeId(id));
        resumeVo.setResumeExperiences(getResumeExperienceByResumeId(id));
        resumeVo.setResumeProjects(getResumeProjectByResumeId(id));
        return resumeVo;
    }

    @Override
    public ResumeEducation getResumeEducationById(String id){
        return tResumeEducationMapper.selectById(id);
    }

    @Override
    public ResumeExperience getResumeExperienceById(String id) {
        return tResumeExperienceMapper.selectById(id);
    }

    @Override
    public ResumeProject getResumeProjectById(String id) {
        return tResumeProjectMapper.selectById(id);
    }

    @Override
    public List<Resume> getResumeByApplicantId(String applicantId) {
        EntityWrapper<Resume> queryWrapper = new  EntityWrapper<Resume>();
        List<Resume> list=tResumeMapper.selectList(queryWrapper.eq("applicant_id",
                applicantId));
        if(list.size()!=0){
            return list;
        }
        else{
            return null;
        }
    }

    @Override
    public List<ResumeEducation> getResumeEducationByResumeId(String resumeId) {
        EntityWrapper<ResumeEducation> queryWrapper = new  EntityWrapper<ResumeEducation>();
        List<ResumeEducation> list=tResumeEducationMapper.selectList(queryWrapper.eq("resume_id",
                resumeId));
        if(list.size()!=0){
            return list;
        }
        else{
            return null;
        }
    }

    @Override
    public List<ResumeExperience> getResumeExperienceByResumeId(String resumeId) {
        EntityWrapper<ResumeExperience> queryWrapper = new  EntityWrapper<ResumeExperience>();
        List<ResumeExperience> list=tResumeExperienceMapper.selectList(queryWrapper.eq("resume_id",
                resumeId));
        if(list.size()!=0){
            return list;
        }
        else{
            return null;
        }
    }

    @Override
    public List<ResumeProject> getResumeProjectByResumeId(String resumeId) {
        EntityWrapper<ResumeProject> queryWrapper = new  EntityWrapper<ResumeProject>();
        List<ResumeProject> list=tResumeProjectMapper.selectList(queryWrapper.eq("resume_id",
                resumeId));
        if(list.size()!=0){
            return list;
        }
        else{
            return null;
        }
    }

    @Override
    public int insertResume(Resume resume) {
        resume.setPosition(" ");
        resume.setSalary("0");
        resume.setCity(" ");
        //设置创建日期
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Date date1 = null;
        try {
            date1 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resume.setDate(date1);
        //插入到简历表
        return tResumeMapper.insert(resume);
    }

    @Override
    public int insertResumeEducation(ResumeEducation resumeEducation) {
        return tResumeEducationMapper.insert(resumeEducation);
    }

    @Override
    public int insertResumeExperience(ResumeExperience resumeExperience) {
        return tResumeExperienceMapper.insert(resumeExperience);
    }

    @Override
    public int insertResumeProject(ResumeProject resumeProject) {
        return tResumeProjectMapper.insert(resumeProject);
    }

    @Override
    public int updateResume(Resume resume) {
        //设置创建日期
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Date date1 = null;
        try {
            date1 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resume.setDate(date1);
        return tResumeMapper.updateById(resume);
    }

    @Override
    public int updateResumeEducation(ResumeEducation resumeEducation) {
        return tResumeEducationMapper.updateById(resumeEducation);
    }

    @Override
    public int updateResumeExperience(ResumeExperience resumeExperience) {
        return tResumeExperienceMapper.updateById(resumeExperience);
    }

    @Override
    public int updateResumeProject(ResumeProject resumeProject) {
        return tResumeProjectMapper.updateById(resumeProject);
    }

    @Override
    public int deleteResume(String id) {
        EntityWrapper<ResumeEducation> queryWrapper1 = new  EntityWrapper<ResumeEducation>();
        EntityWrapper<ResumeExperience> queryWrapper2 = new  EntityWrapper<ResumeExperience>();
        EntityWrapper<ResumeProject> queryWrapper3 = new  EntityWrapper<ResumeProject>();
        tResumeEducationMapper.delete(queryWrapper1.eq("resume_id",id));
        tResumeExperienceMapper.delete(queryWrapper2.eq("resume_id",id));
        tResumeProjectMapper.delete(queryWrapper3.eq("resume_id",id));
        return tResumeMapper.deleteById(id);
    }

    @Override
    public int deleteResumeEducation(String id) {
        return tResumeEducationMapper.deleteById(id);
    }

    @Override
    public int deleteResumeExperience(String id) {
        return tResumeExperienceMapper.deleteById(id);
    }

    @Override
    public int deleteResumeProject(String id) {
        return tResumeProjectMapper.deleteById(id);
    }

    public void insertEducation( List<ResumeEducation> list,String resumeId){
        for(int i=0;i<list.size();i++){
            ResumeEducation resumeEducation=list.get(i);
            resumeEducation.setResumeId(resumeId);
            tResumeEducationMapper.insert(resumeEducation);
        }
    }

    public void insertExperience( List<ResumeExperience> list,String resumeId){
        for(int i=0;i<list.size();i++){
            ResumeExperience resumeExperience=list.get(i);
            resumeExperience.setResumeId(resumeId);
            tResumeExperienceMapper.insert(resumeExperience);
        }
    }

    public void insertProject( List<ResumeProject> list,String resumeId){
        for(int i=0;i<list.size();i++){
            ResumeProject resumeProject=list.get(i);
            resumeProject.setResumeId(resumeId);
            tResumeProjectMapper.insert(resumeProject);
        }
    }
}
