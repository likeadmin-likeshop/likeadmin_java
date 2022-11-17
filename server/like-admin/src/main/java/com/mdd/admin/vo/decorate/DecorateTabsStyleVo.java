package com.mdd.admin.vo.decorate;

import lombok.Data;

import java.io.Serializable;

/**
 * 装修底部导航样式Vo
 */
@Data
public class DecorateTabsStyleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String defaultColor;
    private String selectedColor;

}
