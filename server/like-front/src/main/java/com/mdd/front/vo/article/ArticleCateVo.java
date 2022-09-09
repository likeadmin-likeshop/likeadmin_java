package com.mdd.front.vo.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章分类Vo
 */
@Data
public class ArticleCateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;  // 主键
    private String name; // 名称

}
