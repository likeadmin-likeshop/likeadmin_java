package com.mdd.front.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "个人信息Vo")
public class UserInfoVo implements Serializable {

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

    @ApiModelProperty(value = "用户性别")
    private String sex;

    @ApiModelProperty(value = "是否设置密码")
    private Boolean isPassword;

    @ApiModelProperty(value = "是否绑定微信")
    private Boolean isBindMnp;

    @ApiModelProperty(value = "版本信息")
    private String version;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    public void setSex(Integer sex) {
        switch (sex) {
            case 0:
                this.sex = "未知";
                break;
            case 1:
                this.sex = "男";
                break;
            case 2:
                this.sex = "女";
                break;
        }
    }

}
