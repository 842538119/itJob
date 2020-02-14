package com.itJob.bean.Vo;

import lombok.Data;

/**
 * @Description: 输出结果 类
 * @Author: LRJ
 * @Date: 2019/12/26 20:20
 */
@Data
public class Result {
    private boolean success;//是否成功
    private String message;//返回信息
    private Object data;
}
