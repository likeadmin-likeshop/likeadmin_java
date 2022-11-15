package com.mdd.admin.validate;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章搜索参数
 */
@Data
public class ArticleSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private Integer cid;

    private Integer isShow;

    private String startTime;

    private String endTime;

}
