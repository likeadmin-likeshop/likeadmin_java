package com.mdd.admin.vo.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础设置Vo
 */
@Data
@ApiModel("基础设置Vo")
public class SettingWebsiteVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "网站名称")
    private String name;
    @ApiModelProperty(value = "网站logo")
    private String logo;
    @ApiModelProperty(value = "网站图标")
    private String favicon;
    @ApiModelProperty(value = "登录页广告图")
    private String backdrop;

    @ApiModelProperty(value = "商城名称")
    private String shopName;
    @ApiModelProperty(value = "商城LOGO")
    private String shopLogo;

    @ApiModelProperty(value = "PC端LOGO")
    private String pcLogo;
    @ApiModelProperty(value = "网站标题")
    private String pcTitle;
    @ApiModelProperty(value = "网站图标")
    private String pcIco;
    @ApiModelProperty(value = "网站描述")
    private String pcDesc;
    @ApiModelProperty(value = "网站关键词")
    private String pcKeywords;

}
