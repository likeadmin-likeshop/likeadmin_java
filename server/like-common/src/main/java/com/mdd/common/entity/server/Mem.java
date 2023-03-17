package com.mdd.common.entity.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("內存相关信息实体")
public class Mem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("内存总量")
    private double total;

    @ApiModelProperty("已用内存")
    private double used;

    @ApiModelProperty("剩余内存")
    private double free;

    @ApiModelProperty("使用率")
    private double usage;

}
