package com.itJob;

import com.baomidou.mybatisplus.plugins.Page;
import com.itJob.bean.Po.ApplyPo;
import com.itJob.bean.Po.PositionPo;
import com.itJob.bean.Position;
import com.itJob.bean.Vo.ApplyVo;
import com.itJob.bean.Vo.PositionVo;
import com.itJob.mapper.TApplyMapper;
import com.itJob.mapper.TPositionMapper;
import com.itJob.mapper.TUserMapper;
import com.itJob.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description: 测试 类
 * @Author: LRJ
 * @Date: 2019/11/27 15:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class test1 {
    @Autowired
    private TPositionMapper tPositionMapper;
    @Autowired
    private TApplyMapper tApplyMapper;
    @Autowired
    private TApplyService tApplyService;
    @Autowired
    private TUserService tUserService;
    @Autowired
    private TApplicantService tApplicantService;
    @Autowired
    private TPositionService tPositionService;
    @Autowired
    private TResumeService tResumeService;
    @Test
    public void sss(){
        List<PositionVo> positionVo = tPositionService.getPositionList(1,5,"%JAVA%",null);
        for(int i=0;i<positionVo.size();i++){
            System.out.println(positionVo.get(i).getPosition().getId());
        }
    }
    public class ListNode { int val;ListNode next;ListNode(int x) { val = x; }}


}
