package com.mdd.admin.vo.setting;

import com.mdd.common.entity.setting.HotSearch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("热门搜索详情Vo")
public class SettingSearchDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否开起热门搜索")
    private Integer isHotSearch;

    @ApiModelProperty(value = "热门搜索关键词")
    private List<HotSearch> list;

}
