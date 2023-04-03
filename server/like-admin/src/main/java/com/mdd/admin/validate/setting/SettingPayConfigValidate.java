package com.mdd.admin.validate.setting;

import com.mdd.common.validator.annotation.IDMust;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@ApiModel("支付渠道设置参数")
public class SettingPayConfigValidate {

    @IDMust(message = "id参数必传且需大于0")
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;

    @NotNull(message = "name参数缺失")
    @ApiModelProperty(value = "模版名称", required = true)
    private String name;

    @ApiModelProperty(value = "模版名称")
    private String icon;

    @ApiModelProperty(value = "排序编号")
    private Integer sort;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "配置参数")
    private Map<String, String> params;

}
