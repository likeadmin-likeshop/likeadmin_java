package com.hxkj.admin.vo.common.article;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String category;
    private String title;
    private String image;
    private Integer visit;
    private Integer sort;
    private Integer isShow;
    private String createTime;
    private String updateTime;

}
