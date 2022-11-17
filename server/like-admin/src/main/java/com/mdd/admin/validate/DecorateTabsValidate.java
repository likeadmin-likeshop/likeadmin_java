package com.mdd.admin.validate;


import com.mdd.admin.vo.decorate.DecorateTabsListsVo;
import com.mdd.admin.vo.decorate.DecorateTabsStyleVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 底部导航装修参数
 */
@Data
public class DecorateTabsValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private DecorateTabsStyleVo style;

    private List<DecorateTabsListsVo> list;

}
