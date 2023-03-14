package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 底部导航实体
 */
@Data
public class DecorateTabbar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;         // 主键
    private String name;        // 导航名称
    private String selected;    // 未选图标
    private String unselected;  // 已选图标
    private String link;        // 链接地址
    private Long createTime;    // 创建时间
    private Long updateTime;    // 更新时间

}
