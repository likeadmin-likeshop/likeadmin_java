package com.mdd.front.validate;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("新用户更新信息参数")
public class RechargeValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "payWay参数缺失")
    private Integer payWay;

    @NotNull(message = "orderAmount参数缺失")
    private BigDecimal orderAmount;

}
