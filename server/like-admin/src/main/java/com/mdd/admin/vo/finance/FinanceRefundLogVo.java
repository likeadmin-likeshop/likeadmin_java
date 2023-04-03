package com.mdd.admin.vo.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("退款记录日志Vo")
public class FinanceRefundLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("流水号")
    private String sn;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款状态: [0=退款中, 1=退款成功, 2=退款失败]")
    private Integer refundStatus;

    @ApiModelProperty("退款状态描述")
    private String refundStatusMsg;

    @ApiModelProperty("退款信息")
    private String refundMsg;

    @ApiModelProperty("操作人")
    private String handler;

    @ApiModelProperty("创建时间")
    private String createTime;

}
