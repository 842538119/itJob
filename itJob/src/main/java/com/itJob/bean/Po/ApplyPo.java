package com.itJob.bean.Po;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 投递PO 类
 * @Author: LRJ
 * @Date: 2020/1/20 13:09
 */
@Data
public class ApplyPo {
    String applyId;
    Integer status;
    Date date;
    String positionId;
    String positionName;
    String applicantId;
    String applicantName;
    String sex;
    Integer age;
    String icon;
    String education;
    String city;
    String resumeId;

}
