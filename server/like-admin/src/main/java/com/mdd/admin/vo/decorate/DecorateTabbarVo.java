package com.mdd.admin.vo.decorate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ApiModel("装修底部导航Vo")
public class DecorateTabbarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "样式")
    private Map<String, String> style;

    @ApiModelProperty(value = "列表")
    private List<DecorateTabsListsVo> list;

}
