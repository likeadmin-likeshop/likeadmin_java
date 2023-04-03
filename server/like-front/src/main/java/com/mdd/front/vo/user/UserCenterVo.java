package com.mdd.front.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "个人中心Vo")
public class UserCenterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "用户编号")
    private Integer sn;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户账号")
    private String username;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "钱包余额")
    private BigDecimal money;

    @ApiModelProperty(value = "是否新用户")
    private Integer isNew;

    @ApiModelProperty(value = "是否绑定微信")
    private Boolean isBindWechat;

    @ApiModelProperty(value = "是否有设置登录密码")
    private Boolean hasPwd;

}
