package com.mdd.front.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "充值配置Vo")
public class RechargeConfigVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否开启充值: 0=否,1=是")
    private Integer openRecharge;

    @ApiModelProperty("最低充值金额")
    private BigDecimal minRechargeMoney;

    @ApiModelProperty("用户钱包")
    private BigDecimal userMoney;

}
