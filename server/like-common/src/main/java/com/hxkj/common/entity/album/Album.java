package com.hxkj.common.entity.album;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 相册实体
 */
@Data
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer cid;
    private Integer aid;
    private Integer uid;
    private Integer type;
    private String name;
    private String uri;
    private String ext;
    private Long size;
    private Integer isDelete;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;

}
