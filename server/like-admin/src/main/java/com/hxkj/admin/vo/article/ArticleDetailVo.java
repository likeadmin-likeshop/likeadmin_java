package com.hxkj.admin.vo.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章详情Vo
 */
@Data
public class ArticleDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer cid;
    private String title;
    private String image;
    private String intro;
    private String content;
    private Integer visit;
    private Integer sort;
    private Integer isShow;
    private String createTime;
    private String updateTime;

}
