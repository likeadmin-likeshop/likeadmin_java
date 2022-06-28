package com.hxkj.admin.vo.common.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类Vo
 */
@Data
public class CategoryVo implements Serializable {

    private Integer id;
    private String name;
    private Integer sort;
    private Integer isShow;
    private String createTime;
    private String updateTime;

}
