package com.mdd.admin.vo.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("通知设置列表Vo")
public class SettingNoticeListedVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "通知名称")
    private String name;

    @ApiModelProperty(value = "通知类型")
    private String type;

    @ApiModelProperty(value = "通知状态")
    private Integer systemStatus;

    @ApiModelProperty(value = "通知状态")
    private Integer smsStatus;

    @ApiModelProperty(value = "公众号状态")
    private Integer oaStatus;

    @ApiModelProperty(value = "小程序状态")
    private Integer mnpStatus;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

}
