package com.mdd.front.vo.article;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String image;
    private String intro;
    private String summary;
    private Integer visit;
    private String author;
    private String content;
    private String createTime;

}
