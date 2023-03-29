package com.mdd.front.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "系统登录Vo")
public class LoginTokenVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "绑定手机")
    private Boolean isBindMobile;

    @ApiModelProperty(value = "登录令牌")
    private String token;

    @ApiModelProperty(value = "是否为新用户")
    private Integer isNew;

}
