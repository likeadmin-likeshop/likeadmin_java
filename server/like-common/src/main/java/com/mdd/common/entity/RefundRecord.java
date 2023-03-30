package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("退款记录实体")
public class RefundRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty(value = "退款编号")
    private String sn;

    @ApiModelProperty("关联用户ID")
    private Integer userId;

    @ApiModelProperty("来源订单ID")
    private Integer orderId;

    @ApiModelProperty("来源单号SN")
    private String orderSn;

    @ApiModelProperty("订单类型: [order=商品订单, recharge=充值订单]")
    private String orderType;

    @ApiModelProperty("总应付款金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("本次退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("平台交易流水号")
    private String transactionId;

    @ApiModelProperty("退款方式: 1=线上退款, 2=线下退款")
    private Integer refundWay;

    @ApiModelProperty("退款类型: 1=后台退款")
    private Integer refundType;

    @ApiModelProperty("退款状态: 0=退款中, 1=退款成功, 2=退款失败")
    private Integer refundStatus;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

}
