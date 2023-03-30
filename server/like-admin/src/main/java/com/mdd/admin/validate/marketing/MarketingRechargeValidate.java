package com.mdd.admin.validate.marketing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("订单搜索参数")
public class MarketingRechargeValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "openRecharge参数缺失")
    @ApiModelProperty("是否开启充值: 0=否,1=是")
    private Integer openRecharge;

    @NotNull(message = "minRechargeMoney参数缺失")
    @ApiModelProperty("最低充值金额")
    private BigDecimal minRechargeMoney;

}
