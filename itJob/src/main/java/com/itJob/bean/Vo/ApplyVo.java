package com.itJob.bean.Vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.itJob.bean.*;
import lombok.Data;

/**
 * @Description: 投递展示 类
 * @Author: LRJ
 * @Date: 2020/1/17 22:39
 */
@Data
public class ApplyVo {
    Apply apply;
    Applicant applicant;
    Position position;
    Resume resume;
}
