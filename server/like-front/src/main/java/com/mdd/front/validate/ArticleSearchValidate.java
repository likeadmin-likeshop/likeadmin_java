package com.mdd.front.validate;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章搜索参数
 */
@Data
public class ArticleSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String keyword;

    private String sort;

}
