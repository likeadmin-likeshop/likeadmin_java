package com.mdd.front.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PC端文章详情
 */
@Data
public class PcArticleDetailVo implements Serializable {

    private Integer id;
    private Integer cid;
    private String category;
    private String intro;
    private String summary;
    private String image;
    private String content;
    private String author;
    private Integer visit;
    private Integer sort;
    private Integer isCollect;
    private String createTime;
    private String updateTime;
    private Object prev;
    private Object next;


}
