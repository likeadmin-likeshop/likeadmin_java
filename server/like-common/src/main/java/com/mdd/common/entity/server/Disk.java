package com.mdd.common.entity.server;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统文件信息实体")
public class Disk implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("盘符路径")
    private String dirName;

    @ApiModelProperty("盘符类型")
    private String sysTypeName;

    @ApiModelProperty("文件类型")
    private String typeName;

    @ApiModelProperty("总大小")
    private String total;

    @ApiModelProperty("剩余大小")
    private String free;

    @ApiModelProperty("已经使用量")
    private String used;

    @ApiModelProperty("资源的使用率")
    private double usage;

}
