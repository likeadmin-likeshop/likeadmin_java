package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统操作日志实体")
public class SystemLogOperate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("操作人ID")
    private Integer adminId;

    @ApiModelProperty("请求类型: GET/POST/PUT")
    private String type;

    @ApiModelProperty("操作标题")
    private String title;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("请求IP")
    private String ip;

    @ApiModelProperty("请求接口")
    private String url;

    @ApiModelProperty("请求参数")
    private String args;

    @ApiModelProperty("错误信息")
    private String error;

    @ApiModelProperty("执行状态: [1=成功, 2=失败]")
    private Integer status;

    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

    @ApiModelProperty("执行耗时")
    private Long taskTime;

    @ApiModelProperty("创建时间")
    private Long createTime;

}
