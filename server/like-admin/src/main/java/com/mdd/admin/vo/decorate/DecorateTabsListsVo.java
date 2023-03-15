package com.mdd.admin.vo.decorate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ApiModel("装修底部导航列表Vo")
public class DecorateTabsListsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "导航名称")
    private String name;

    @ApiModelProperty(value = "已选图标")
    private String selected;

    @ApiModelProperty(value = "未选图标")
    private String unselected;

    @ApiModelProperty(value = "id")
    private Object link;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

}
