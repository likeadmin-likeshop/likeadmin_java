package com.mdd.front.vo.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "支付状态Vo")
public class PayStatusVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "支付状态: [0=待支付, 1=已支付]")
    private Integer payStatus;

    @ApiModelProperty(value = "支付状态: [0=待支付, 1=已支付]")
    private String payWay;

    @ApiModelProperty(value = "支付时间")
    private String payTime;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

}
