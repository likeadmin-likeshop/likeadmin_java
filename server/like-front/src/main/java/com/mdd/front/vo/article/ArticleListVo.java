package com.mdd.front.vo.article;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String image;
    private String intro;
    private Integer visit;
    private String createTime;

}
