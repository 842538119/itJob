package com.itJob.bean.Vo;

import com.itJob.bean.PositionType;
import com.itJob.bean.Position;
import com.itJob.bean.Enterprise;
import lombok.Data;

/**
 * @Description: 职位展示 类
 * @Author: LRJ
 * @Date: 2020/1/2 21:31
 */
@Data
public class PositionVo {
    Enterprise enterprise;
    Position position;
    PositionType positionType;
}
