package com.mdd.admin.vo.decorate;

import lombok.Data;

@Data
public class DecorateDataArticleVo {

    private Integer id;
    private String title;
    private String intro;
    private String summary;
    private String image;
    private String author;
    private Integer visit;
    private String createTime;

}
