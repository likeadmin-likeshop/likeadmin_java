package com.mdd.admin.vo.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("热门搜索对象Vo")
public class SettingSearchObjectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关键词")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
