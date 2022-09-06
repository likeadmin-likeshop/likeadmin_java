package com.mdd.front.vo.article;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleCollectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer articleId;
    private String title;
    private String image;
    private String intro;
    private Integer visit;
    private String createTime;

}
