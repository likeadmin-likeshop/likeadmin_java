package com.mdd.admin.validate.decorate;

import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("页面装修参数")
public class DecoratePageValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    @ApiModelProperty(value = "id", required = true)
    private Integer id;

    @NotNull(message = "pageData参数缺失")
    @ApiModelProperty(value = "装修数据", required = true)
    private String pageData;

}
