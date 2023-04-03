package com.mdd.admin.vo.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("余额记录列表Vo")
public class FinanceWalletListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录ID")
    private Integer id;

    @ApiModelProperty(value = "用户编号")
    private String userSn;

    @ApiModelProperty(value = "u屏幕估计头皮屑")
    private String avatar;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "变动金额")
    private BigDecimal changeAmount;

    @ApiModelProperty(value = "剩余金额")
    private BigDecimal leftAmount;

    @ApiModelProperty(value = "变动类型")
    private String changeType;

    @ApiModelProperty(value = "来源单号")
    private String sourceSn;

    @ApiModelProperty(value = "记录时间")
    private String createTime;

}
