package com.mdd.front.vo.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章收藏Vo
 */
@Data
public class ArticleCollectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;         // 主键
    private Integer articleId;  // 文章ID
    private String title;       // 文章标题
    private String image;       // 文章封面
    private String intro;       // 文章简介
    private Integer visit;      // 浏览数量
    private String createTime;  // 创建时间

}
