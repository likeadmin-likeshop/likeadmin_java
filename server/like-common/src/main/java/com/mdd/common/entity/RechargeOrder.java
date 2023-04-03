package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("充值订单实体类")
public class RechargeOrder {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("支付编号")
    private String paySn;

    @ApiModelProperty("支付方式: [2=微信支付, 3=支付宝支付]")
    private Integer payWay;

    @ApiModelProperty("支付状态: [0=待支付, 1=已支付]")
    private Integer payStatus;

    @ApiModelProperty("支付时间")
    private Long payTime;

    @ApiModelProperty("充值金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("下单终端")
    private Integer orderTerminal;

    @ApiModelProperty("交易流水")
    private String transactionId;

    @ApiModelProperty("退款状态: [0=未退款 , 1=已退款]")
    private Integer refundStatus;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}
