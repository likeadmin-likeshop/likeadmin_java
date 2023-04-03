package com.mdd.admin.vo.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("退款记录列表Vo")
public class FinanceRefundListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("记录ID")
    private Integer id;

    @ApiModelProperty("退款编号")
    private String sn;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("来源单号SN")
    private String orderSn;

    @ApiModelProperty("总应付款金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("本次退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款类型: 1=后台退款")
    private Integer refundType;

    @ApiModelProperty("退款状态: 0=退款中, 1=退款成功, 2=退款失败")
    private Integer refundStatus;

    @ApiModelProperty("退款类型描述")
    private String refundTypeMsg;

    @ApiModelProperty("退款状态描述")
    private String refundStatusMsg;

    @ApiModelProperty("记录时间")
    private String createTime;

}
