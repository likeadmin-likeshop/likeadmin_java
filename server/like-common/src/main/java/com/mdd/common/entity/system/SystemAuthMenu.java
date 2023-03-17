package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统菜单实体")
public class SystemAuthMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("上级菜单")
    private Integer pid;

    @ApiModelProperty("权限类型: [M=目录, C=菜单, A=按钮]")
    private String menuType;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty("菜单排序")
    private Integer menuSort;

    @ApiModelProperty("权限标识")
    private String perms;

    @ApiModelProperty("路由地址")
    private String paths;

    @ApiModelProperty("前端组件")
    private String component;

    @ApiModelProperty("选中路径")
    private String selected;

    @ApiModelProperty("路由参数")
    private String params;

    @ApiModelProperty("是否缓存: [0=否, 1=是]")
    private Integer isCache;

    @ApiModelProperty("是否显示: [0=否, 1=是]")
    private Integer isShow;

    @ApiModelProperty("是否禁用: [0=否, 1=是]")
    private Integer isDisable;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

}
