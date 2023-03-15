package com.mdd.admin.vo.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("通知设置详情Vo")
public class SettingNoticeDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "场景名称")
    private String name;

    @ApiModelProperty(value = "通知类型: [1=业务, 2=验证]")
    private String type;

    @ApiModelProperty(value = "场景描述")
    private String remarks;

    @ApiModelProperty(value = "系统的通知设置")
    private Object systemNotice;

    @ApiModelProperty(value = "公众号通知设置")
    private Object oaNotice;

    @ApiModelProperty(value = "小程序通知设置")
    private Object mnpNotice;

    @ApiModelProperty(value = "短信的通知设置")
    private Object smsNotice;

}
