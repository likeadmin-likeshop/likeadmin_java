package com.mdd.admin.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统菜单Vo")
public class SystemAuthMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "上级菜单")
    private Integer pid;

    @ApiModelProperty(value = "权限类型: [M=目录, C=菜单, A=按钮]")
    private String menuType;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    @ApiModelProperty(value = "菜单排序")
    private Integer menuSort;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "路由地址")
    private String paths;

    @ApiModelProperty(value = "前端组件")
    private String component;

    @ApiModelProperty(value = "选中路径")
    private String selected;

    @ApiModelProperty(value = "路由参数")
    private String params;

    @ApiModelProperty(value = "是否缓存: [0=否, 1=是]")
    private Integer isCache;

    @ApiModelProperty(value = "是否显示: [0=否, 1=是]")
    private Integer isShow;

    @ApiModelProperty(value = "是否禁用: [0=否, 1=是]")
    private Integer isDisable;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

}
