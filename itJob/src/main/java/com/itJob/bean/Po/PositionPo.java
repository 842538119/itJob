package com.itJob.bean.Po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 职位PO 类
 * @Author: LRJ
 * @Date: 2020/1/18 22:16
 */
@Data
public class PositionPo {
    String positionTypeId;
    String positionTypeName;
    String enterpriseId;
    String enterpriseName;
    String icon;
    String type;
    String financing;
    String phone;
    String email;
    String address;
    String introduction;
    String positionId;
    String positionName;
    String salary;
    Integer positionStatus;
    String city;
    String education;
    String experience;
    String duties;
    @TableField(value = "position_status")
    Integer status;;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    Date positionDate;
}
