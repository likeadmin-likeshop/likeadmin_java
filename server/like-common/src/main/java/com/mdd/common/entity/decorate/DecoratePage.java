package com.mdd.common.entity.decorate;

import lombok.Data;

import java.io.Serializable;

/**
 * 页面装修实体
 */
@Data
public class DecoratePage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer pageType;
    private String pageName;
    private String pageData;
    private Long createTime;
    private Long updateTime;

}
