package com.mdd.admin.vo.decorate;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 装修底部导航列表Vo
 */
@Data
public class DecorateTabsListsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String selected;
    private String unselected;
    private Object link;
    private String createTime;
    private String updateTime;

}
