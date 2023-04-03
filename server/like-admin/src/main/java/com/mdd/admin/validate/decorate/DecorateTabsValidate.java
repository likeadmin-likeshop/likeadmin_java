package com.mdd.admin.validate.decorate;

import com.mdd.admin.vo.decorate.DecorateTabsListsVo;
import com.mdd.admin.vo.decorate.DecorateTabsStyleVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 底部导航装修参数
 */
@Data
@ApiModel("底部装修参数")
public class DecorateTabsValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "样式")
    private DecorateTabsStyleVo style;

    @ApiModelProperty(value = "列表")
    private List<DecorateTabsListsVo> list;

}
