package com.mdd.admin.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("登录日志Vo")
public class SystemLogLoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "登录账号")
    private String username;

    @ApiModelProperty(value = "来源IP")
    private String ip;

    @ApiModelProperty(value = "操作系统")
    private String os;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "操作状态: [1=成功, 2=失败]")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

}
