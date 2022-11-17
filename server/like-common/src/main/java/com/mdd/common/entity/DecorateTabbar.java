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
    private Integer id;
    private String name;
    private String selected;
    private String unselected;
    private String link;
    private Long createTime;
    private Long updateTime;

}
