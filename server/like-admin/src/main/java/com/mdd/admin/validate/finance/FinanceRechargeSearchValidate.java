package com.mdd.admin.validate.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("充值订单搜索参数")
public class FinanceRechargeSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    private String sn;

    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "支付方式")
    private Integer payWay;

    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;

    @ApiModelProperty(value = "开始时间")
    private Integer startTime;

    @ApiModelProperty(value = "结束时间")
    private Integer endTime;

}
