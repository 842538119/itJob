package com.itJob.bean;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @Description: 收藏 类
 * @Author: LRJ
 * @Date: 2020/1/23 20:06
 */
@Data
@TableName("t_collection")
public class Collection {
    @TableId(value = "collection_id",type = IdType.UUID)
    String id;
    String applicantId;
    String positionId;
}
