package com.hxkj.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章实体
 */
@Data
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer cid;
    private String title;
    private String intro;
    private String image;
    private String content;
    private Integer visit;
    private Integer sort;
    private Integer isShow;
    private Integer isDelete;
    private Long createTime;
    private Long updateTime;
    private  Long deleteTime;

}
