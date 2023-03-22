package com.mdd.front.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "充值记录Vo")
public class RechargeRecordVo implements Serializable {

    @ApiModelProperty(value = "订单ID")
    private Integer id;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "充值描述")
    private String tips;

    @ApiModelProperty(value = "充值时间")
    private String payTime;

}
