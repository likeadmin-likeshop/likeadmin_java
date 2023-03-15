package com.mdd.admin.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("操作日志Vo")
public class SystemLogOperateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户账号")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "请求类型: GET/POST/PUT")
    private String type;

    @ApiModelProperty(value = "操作标题")
    private String title;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "请求IP")
    private String ip;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "请求参数")
    private String args;

    @ApiModelProperty(value = "错误信息")
    private String error;

    @ApiModelProperty(value = "执行状态: [1=成功, 2=失败]")
    private Integer status;

    @ApiModelProperty(value = "执行耗时")
    private String taskTime;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

}
