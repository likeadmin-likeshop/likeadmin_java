package com.hxkj.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 相册分类实体
 */
@Data
public class AlbumCate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer pid;
    private Integer type;
    private String name;
    private Integer isDelete;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;

}
