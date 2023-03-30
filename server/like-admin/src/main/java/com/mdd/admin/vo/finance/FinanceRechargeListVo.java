package com.mdd.admin.vo.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("充值记录列表Vo")
public class FinanceRechargeListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户编号")
    private String userSn;

    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("支付方式: [2=微信支付, 3=支付宝支付]")
    private String payWay;

    @ApiModelProperty("支付状态: [0=待支付, 1=已支付]")
    private Integer payStatus;

    @ApiModelProperty("支付金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("支付时间")
    private String payTime;

    @ApiModelProperty("创建时间")
    private String createTime;

}
