package com.mdd.admin.vo.decorate;

import lombok.Data;

import java.io.Serializable;

/**
 * 装修页面Vo
 */
@Data
public class DecoratePageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer pageType;
    private String pageData;

}
