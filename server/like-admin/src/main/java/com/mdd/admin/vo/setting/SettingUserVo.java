package com.mdd.admin.vo.setting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户设置Vo")
public class SettingUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "默认头像")
    private String defaultAvatar;

}
