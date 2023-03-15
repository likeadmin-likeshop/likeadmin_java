package com.mdd.admin.validate.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统岗位搜索参数")
public class SystemPostSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "岗位名称")
    private String name;

    @ApiModelProperty(value = "是否停用")
    private Integer isStop;

}
