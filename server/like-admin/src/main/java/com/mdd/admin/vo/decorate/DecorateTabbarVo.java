package com.mdd.admin.vo.decorate;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 装修底部导航Vo
 */
@Data
public class DecorateTabbarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, String> style;
    private List<DecorateTabsListsVo> list;

}
