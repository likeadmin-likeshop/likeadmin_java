package com.mdd.front.validate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("预支付订单参数")
public class PaymentValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "scene参数缺失")
    @ApiModelProperty("支付场景")
    private String scene;

    @NotNull(message = "payWay参数缺失")
    @ApiModelProperty("支付方式")
    private Integer payWay;

    @NotNull(message = "orderId参数缺失")
    @ApiModelProperty("订单ID")
    private Integer orderId;

    private Integer userId;

    private String orderSn;

    private BigDecimal orderAmount;

    private String description;

}
