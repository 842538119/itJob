package com.itJob.controller;

import com.itJob.bean.PositionType;
import com.itJob.bean.Position;
import com.itJob.bean.Vo.PositionVo;
import com.itJob.bean.Vo.Result;
import com.itJob.service.TPositionService;
import com.itJob.service.TPositionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 职位Controller 类
 * @Author: LRJ
 * @Date: 2020/1/2 21:41
 */
@RestController
@RequestMapping("/position")
public class TPositionController {
    @Autowired
    private TPositionService tPositionService;
    @Autowired
    private TPositionTypeService tPositionTypeService;
    /**
     * @Description: 获得岗位类别列表 方法
     * @param:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/8 20:41
     */
    @RequestMapping("/getPositionTypeList")
    public Result getPositionTypeList(){
        Result result=new Result();
        List<PositionType> list=tPositionTypeService.getPositionTypeList();
        result.setSuccess(true);
        result.setData(list);
        return result;
    }
    /**
     * @Description: 主键获得职位和企业 方法
     * @param: * @Param id:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/8 19:54
     */
    @RequestMapping("/getOneById")
    public Result getOneById(String id){
        Result result=new Result();
        PositionVo positionVo=tPositionService.getOneById(id);
        result.setSuccess(true);
        result.setData(positionVo);
        return result;
    }
    /**
     * @Description: 获得分页职位列表 方法
     * @param: * @Param PageNum:
     * @Param PageSize:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/8 19:54
     */
    @RequestMapping("/getPositionList")
    public Result getPositionList(Integer pageNum, Integer pageSize,String name,String city){
        System.out.println(name+"  "+city);
        Result result=new Result();
        List<PositionVo> positionList=tPositionService.getPositionList(pageNum,pageSize,name,city);
        result.setSuccess(true);
        result.setData(positionList);
        return result;
    }
    /**
     * @Description: 企业ID获得发布的职位 方法
     * @param: * @Param enterpriseId:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/8 19:55
     */
    @RequestMapping("/getPositionListByEnterpriseId")
    public Result getPositionListByEnterpriseId(String enterpriseId){
        Result result=new Result();
        List<PositionVo> positionList=tPositionService.getPositionListByEnterpriseId(enterpriseId);
        result.setSuccess(true);
        result.setData(positionList);
        return result;
    }
    /**
     * @Description: 新增职位
     * @param: * @Param position:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/8 19:53
     */
    @RequestMapping("/insertPosition")
    public Result insertPosition(@RequestBody Position position){
        Result result=new Result();
        //修改状态为待审核
        position.setStatus(0);
        int count= tPositionService.insert(position);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("提交职位成功，请等待管理员审核！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("提交职位失败，请重试！");
        }
        return result;
    }
    /**
     * @Description: 更新职位
     * @param: * @Param position:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/8 19:53
     */
    @RequestMapping("/updatePosition")
    public Result updatePosition(@RequestBody Position position){
        Result result=new Result();
        //修改状态为待审核
        position.setStatus(0);
        int count= tPositionService.update(position);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("修改职位成功，请等待管理员审核！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("修改职位失败，请重试！");
        }
        return result;
    }
    /**
     * @Description: 撤销职位
     * @param: * @Param position:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/8 19:52
     */
    @RequestMapping("/revokePosition")
    public Result revokePosition(@RequestBody Position position){
        Result result=new Result();
        //修改状态为已撤销
        position.setStatus(2);
        int count= tPositionService.update(position);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("撤销职位成功！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("撤销职位失败！");
        }
        return result;
    }
    /**
     * @Description: 删除职位
     * @param: * @Param id:
     * @return: com.itJob.bean.Vo.Result
     * @auther: LRJ
     * @date: 2020/1/8 19:53
     */
    @RequestMapping("/deletePosition")
    public Result deletePosition(String id){
        Result result=new Result();
        int count= tPositionService.delete(id);
        if(count!=0){
            result.setSuccess(true);
            result.setMessage("删除职位成功！");

        }
        else{
            result.setSuccess(false);
            result.setMessage("删除失败！");
        }
        return result;
    }
}
