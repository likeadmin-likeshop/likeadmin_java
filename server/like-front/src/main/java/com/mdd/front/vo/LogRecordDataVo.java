package com.mdd.front.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "日志记录Vo")
public class LogRecordDataVo implements Serializable {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "描述")
    private String tips;

    @ApiModelProperty(value = "操作")
    private Integer action;

    @ApiModelProperty(value = "时间")
    private String createTime;

}
