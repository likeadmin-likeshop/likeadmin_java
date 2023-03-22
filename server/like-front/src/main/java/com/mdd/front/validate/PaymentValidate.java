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

    @ApiModelProperty(value = "用户ID", notes = "该参数无需传递")
    private Integer userId;

    @ApiModelProperty(value = "订单类型", notes = "该参数无需传递")
    private String attach;

    @ApiModelProperty(value = "订单编号", notes = "该参数无需传递")
    private String orderSn;

    @ApiModelProperty(value = "订单金额", notes = "该参数无需传递")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "用户描述", notes = "该参数无需传递")
    private String description;

}
