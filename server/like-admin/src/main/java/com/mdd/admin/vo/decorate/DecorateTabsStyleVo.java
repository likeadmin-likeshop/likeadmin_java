package com.mdd.admin.vo.decorate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("装修底部导航样式Vo")
public class DecorateTabsStyleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "默认颜色")
    private String defaultColor;

    @ApiModelProperty(value = "选中颜色")
    private String selectedColor;

}
