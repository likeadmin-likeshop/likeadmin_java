package com.mdd.admin.vo.decorate;

import lombok.Data;

import java.io.Serializable;

/**
 * 装修文章数据Vo
 */
@Data
public class DecorateDataArticleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String intro;
    private String summary;
    private String image;
    private String author;
    private Integer visit;
    private String createTime;

}
