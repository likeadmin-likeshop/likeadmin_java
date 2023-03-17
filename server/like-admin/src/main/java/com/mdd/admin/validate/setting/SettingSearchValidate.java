package com.mdd.admin.validate.setting;

import com.mdd.admin.vo.setting.SettingSearchObjectVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("热门搜索设置参数")
public class SettingSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否开启热门搜索")
    private Integer isHotSearch;

    @ApiModelProperty(value = "关键词列表")
    private List<SettingSearchObjectVo> list;

}
