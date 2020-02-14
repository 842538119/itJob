package com.itJob.controller;

import com.itJob.bean.Resume;
import com.itJob.bean.ResumeEducation;
import com.itJob.bean.ResumeExperience;
import com.itJob.bean.ResumeProject;
import com.itJob.bean.Vo.Result;
import com.itJob.bean.Vo.ResumeVo;
import com.itJob.service.TResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 简历controller 类
 * @Author: LRJ
 * @Date: 2020/1/12 21:06
 */
@RestController
@RequestMapping("/resume")
public class TResumeController {
    @Autowired
    private TResumeService tResumeService;
    /**
     * @Description: 求职者ID获得简历列表 方法
     * @param: * @Param applicantId: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/12 22:01
     */
    @RequestMapping("/getResumeByApplicantId")
    public Result getResumeByApplicantId(String applicantId){
        Result result=new Result();
        List<Resume> list=tResumeService.getResumeByApplicantId(applicantId);
        result.setData(list);
        result.setSuccess(true);
        return result;
    }
    /**
     * @Description: 简历ID获得简历方法
     * @param: * @Param id:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/13 22:32
     */
    @RequestMapping("/getOneById")
    public Result getOneById(String id){
        Result result=new Result();
        ResumeVo resumeVo=tResumeService.getOneById(id);
        result.setData(resumeVo);
        result.setSuccess(true);
        return result;
    }
    /**
     * @Description: ID获得教育经历方法
     * @param: * @Param id: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/14 19:07
     */
    @RequestMapping("/getResumeEducationById")
    public Result getResumeEducationById(String id){
        Result result=new Result();
        ResumeEducation resumeEducation=tResumeService.getResumeEducationById(id);
        result.setData(resumeEducation);
        result.setSuccess(true);
        return result;
    }
    /**
     * @Description: ID获得实习经历方法
     * @param: * @Param id: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/14 19:07
     */
    @RequestMapping("/getResumeExperienceById")
    public Result getResumeExperienceById(String id){
        Result result=new Result();
        ResumeExperience resumeExperience=tResumeService.getResumeExperienceById(id);
        result.setData(resumeExperience);
        result.setSuccess(true);
        return result;
    }
    /**
     * @Description: ID获得项目经历方法
     * @param: * @Param id: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/14 19:06
     */
    @RequestMapping("/getResumeProjectById")
    public Result getResumeProjectById(String id){
        Result result=new Result();
        ResumeProject resumeProject=tResumeService.getResumeProjectById(id);
        result.setData(resumeProject);
        result.setSuccess(true);
        return result;
    }
    /**
     * @Description: 新增简历方法
     * @param: * @Param resume:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/13 22:32
     */
    @RequestMapping(value = "/insertResume",method = RequestMethod.POST)
    public Result insertResume(@RequestBody Resume resume){
        Result result=new Result();
        int count=tResumeService.insertResume(resume);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("新增简历成功！");
            return result;
        }
        else{
            result.setSuccess(false);
            result.setMessage("新增简历失败！");
            return result;
        }
    }
    /**
     * @Description: 新增实习经历方法
     * @param: * @Param resumeExperience:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/13 22:32
     */
    @RequestMapping(value = "/insertResumeExperience",method = RequestMethod.POST)
    public Result insertResumeExperience(@RequestBody ResumeExperience resumeExperience){
        Result result=new Result();
        int count=tResumeService.insertResumeExperience(resumeExperience);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("新增项目经历成功！");
            return result;
        }
        else{
            result.setSuccess(false);
            result.setMessage("新增项目经历失败！");
            return result;
        }
    }
    /**
     * @Description: 新增教育经历方法
     * @param: * @Param resumeEducation:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/13 22:33
     */
    @RequestMapping(value = "/insertResumeEducation",method = RequestMethod.POST)
    public Result insertResumeEducation(@RequestBody ResumeEducation resumeEducation){
        Result result=new Result();
        int count=tResumeService.insertResumeEducation(resumeEducation);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("新增教育经历成功！");
            return result;
        }
        else{
            result.setSuccess(false);
            result.setMessage("新增教育经历失败！");
            return result;
        }
    }
    /**
    * @Description: 新增项目经历方法
    * @param: * @Param resumeProject:
    * @return: com.itJob.bean.Vo.Result
    * @auther: LRJ
    * @date: 2020/1/13 22:33
    */
    @RequestMapping(value = "/insertResumeProject",method = RequestMethod.POST)
    public Result insertResumeProject(@RequestBody ResumeProject resumeProject){
        Result result=new Result();
        int count=tResumeService.insertResumeProject(resumeProject);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("新增项目经历成功！");
            return result;
        }
        else{
            result.setSuccess(false);
            result.setMessage("新增项目经历失败！");
            return result;
        }
    }
    /**
     * @Description: 更新简历方法
     * @param: * @Param resume:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/13 22:33
     */
    @RequestMapping(value = "/updateResume",method = RequestMethod.PUT)
    public Result updateResume(@RequestBody Resume resume){
        Result result=new Result();
        int count=tResumeService.updateResume(resume);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("更新简历信息成功！");
            return result;
        }
        else{
            result.setSuccess(false);
            result.setMessage("更新简历信息失败！");
            return result;
        }
    }
    /**
     * @Description: 更新实习经历方法
     * @param: * @Param resumeExperience:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/13 22:34
     */
    @RequestMapping(value="/updateResumeExperience",method = RequestMethod.PUT)
    public Result updateResumeExperience(@RequestBody ResumeExperience resumeExperience) {
        Result result=new Result();
        int count=tResumeService.updateResumeExperience(resumeExperience);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("更新实习信息成功！");
            return result;
        }
        else{
            result.setSuccess(false);
            result.setMessage("更新实习信息失败！");
            return result;
        }
    }
    /**
     * @Description: 更新教育经历方法
     * @param: * @Param resumeEducation:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/13 22:34
     */
    @RequestMapping(value = "/updateResumeEducation",method = RequestMethod.PUT)
    public Result updateResumeEducation(@RequestBody ResumeEducation resumeEducation){
        Result result=new Result();
        System.out.println(resumeEducation.toString());
        int count=tResumeService.updateResumeEducation(resumeEducation);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("更新教育信息成功！");
            return result;
        }
        else{
            result.setSuccess(false);
            result.setMessage("更新教育信息失败！");
            return result;
        }
    }
    /**
     * @Description: 更新项目经历方法
     * @param: * @Param resumeProject:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/13 22:34
     */
    @RequestMapping(value = "/updateResumeProject",method = RequestMethod.PUT)
    public Result updateResumeProject(@RequestBody ResumeProject resumeProject){
        Result result=new Result();
        int count=tResumeService.updateResumeProject(resumeProject);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("更新项目信息成功！");
            return result;
        }
        else{
            result.setSuccess(false);
            result.setMessage("更新项目信息失败！");
            return result;
        }
    }
    /**
     * @Description: 删除简历方法
     * @param: * @Param id: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/14 19:05
     */
    @RequestMapping(value = "/deleteResume/{id}",method = RequestMethod.DELETE)
    public Result deleteResume(@PathVariable("id") String id){
        Result result=new Result();
        int count= tResumeService.deleteResume(id);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("删除简历成功！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("删除简历失败！");
        }
        return result;
    }
    /**
     * @Description: 删除教育经历方法
     * @param: * @Param id: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/14 19:06
     */
    @RequestMapping(value = "/deleteResumeEducation/{id}",method = RequestMethod.DELETE)
    public Result deleteResumeEducation(@PathVariable("id") String id){
        Result result=new Result();
        int count= tResumeService.deleteResumeEducation(id);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("删除教育经历成功！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("删除教育经历失败！");
        }
        return result;
    }
    /**
     * @Description: 删除实习经历方法
     * @param: * @Param id: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/14 19:06
     */
    @RequestMapping(value = "/deleteResumeExperience/{id}",method = RequestMethod.DELETE)
    public Result deleteResumeExperience(@PathVariable("id") String id){
        Result result=new Result();
        int count= tResumeService.deleteResumeExperience(id);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("删除实习经历成功！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("删除实习经历失败！");
        }
        return result;
    }
    /**
     * @Description: 删除项目经历方法
     * @param: * @Param id: 
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/14 19:06
     */
    @RequestMapping(value = "/deleteResumeProject/{id}",method = RequestMethod.DELETE)
    public Result deleteResumeProject(@PathVariable("id") String id){
        Result result=new Result();
        int count= tResumeService.deleteResumeProject(id);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("删除项目经历成功！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("删除项目经历失败！");
        }
        return result;
    }
}
