package com.mdd.admin.validate.finance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("余额记录搜索参数")
public class FinanceWalletSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Integer startTime;

    @ApiModelProperty(value = "结束时间")
    private Integer endTime;

}
