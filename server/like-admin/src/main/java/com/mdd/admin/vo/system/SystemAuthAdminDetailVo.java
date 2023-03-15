package com.mdd.admin.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("管理员详情Vo")
public class SystemAuthAdminDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "角色ID")
    private List<Integer> roleIds;

    @ApiModelProperty(value = "部门ID")
    private List<Integer> deptIds;

    @ApiModelProperty(value = "岗位ID")
    private List<Integer> postIds;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "多端登录: [0=否, 1=是]")
    private Integer isMultipoint;

    @ApiModelProperty(value = "是否禁用: [0=否, 1=是]")
    private Integer isDisable;

    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;

    @ApiModelProperty(value = "最后登录时间")
    private String lastLoginTime;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

}
