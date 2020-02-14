package com.itJob.controller;



import com.itJob.bean.Collection;
import com.itJob.bean.Vo.PositionVo;
import com.itJob.bean.Vo.Result;
import com.itJob.service.TCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 收藏controller 类
 * @Author: LRJ
 * @Date: 2020/1/23 20:43
 */
@RestController
@RequestMapping("/collection")
public class TCollectionController {
    @Autowired
    private TCollectionService tCollectionService;
    /**
     * @Description: 获得用户收藏列表方法
     * @param: * @Param applicantId:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/23 22:45
     */
    @RequestMapping("/getCollectionListByApplicantId")
    public Result getCollectionListByApplicantId(String applicantId){
        Result result=new Result();
        List<PositionVo> list =tCollectionService.getCollectionListByApplicantId(applicantId);
        result.setData(list);
        result.setSuccess(true);
        return result;
    }
    /**
     * @Description: 用户是否收藏某职位方法
     * @param: * @Param collection:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/23 22:45
     */
    @RequestMapping("/isCollected")
    public Result isCollected(Collection collection){
        Result result=new Result();
        if(tCollectionService.isPositionCollected(collection)){
            result.setData("true");
        }
        else{
            result.setData("false");
        }
        result.setSuccess(true);
        return result;
    }
    /**
     * @Description: 新增收藏信息方法
     * @param: * @Param collection:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/23 22:45
     */
    @RequestMapping(value = "/insertCollection",method = RequestMethod.POST)
    public Result insert(@RequestBody Collection collection){
        Result result=new Result();
        int count=tCollectionService.insert(collection);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("收藏成功！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("收藏失败！");
        }
        return result;
    }
    /**
     * @Description: 取消收藏信息方法
     * @param: * @Param collection:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/23 22:46
     */
    @RequestMapping(value = "/deleteCollection",method = RequestMethod.POST)
    public Result delete(@RequestBody Collection collection){
        Result result=new Result();
        int count=tCollectionService.delete(collection);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("取消收藏成功！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("取消收藏失败！");
        }
        return result;
    }
}
