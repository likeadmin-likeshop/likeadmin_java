package com.mdd.admin.validate.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("退款记录搜索参数")
public class FinanceRefundSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("筛选类型: -1=全部, 0=退款中, 1=退款成功, 2=退款失败")
    private Integer type;

    @ApiModelProperty("用户信息")
    private String keyword;

    @ApiModelProperty("退款单号")
    private String sn;

    @ApiModelProperty("来源单号")
    private String orderSn;

    @ApiModelProperty("退款类型: 1=后台退款")
    private Integer refundType;

    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

}
