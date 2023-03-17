package com.mdd.front.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "跳转链接Vo")
public class LoginUrlsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "http链接")
    private String url;

}
