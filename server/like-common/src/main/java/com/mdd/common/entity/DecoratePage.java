package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 页面装修实体
 */
@Data
public class DecoratePage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;       // 主键
    private Integer pageType; // 页面类型
    private String pageName;  // 页面名称
    private String pageData;  // 页面数据
    private Long createTime;  // 创建时间
    private Long updateTime;  // 更新时间

}
