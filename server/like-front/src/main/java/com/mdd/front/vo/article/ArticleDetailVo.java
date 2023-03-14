package com.mdd.front.vo.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章详情Vo
 */
@Data
public class ArticleDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;         // 主键
    private String title;       // 文章标题
    private String image;       // 文章封面
    private String intro;       // 文章简介
    private String summary;     // 文章描述
    private Integer visit;      // 浏览数量
    private String author;      // 作者名称
    private Boolean collect;    // 是否收藏
    private String content;     // 文章内容
    private String createTime;  // 创建时间

}
