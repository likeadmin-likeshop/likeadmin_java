package com.mdd.admin.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("当前系统管理员Vo")
public class SystemAuthAdminSelvesVo {

    @ApiModelProperty(value = "用户信息")
    private Object user;

    @ApiModelProperty(value = "权限集合: [[*]=>所有权限, ['article:add']=>部分权限]")
    private Object permissions;

}
