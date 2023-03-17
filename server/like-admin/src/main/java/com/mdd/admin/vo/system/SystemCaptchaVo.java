package com.mdd.admin.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("验证码")
public class SystemCaptchaVo {

    @ApiModelProperty(value = "标识")
    private String uuid;

    @ApiModelProperty(value = "图片")
    private String img;

}
