package com.mdd.admin.validate.setting;

import com.mdd.common.validator.annotation.IntegerContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("字典类型创建参数")
public class DictTypeCreateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "dictName参数缺失")
    @Length(max = 200, message = "名称不能超出200个字符")
    @ApiModelProperty(value = "名称", required = true)
    private String dictName;

    @NotNull(message = "dictType参数缺失")
    @Length(max = 200, message = "类型不能超出200个字符")
    @ApiModelProperty(value = "类型", required = true)
    private String dictType;

    @Length(max = 200, message = "备注不能超出200个字符")
    @ApiModelProperty(value = "备注")
    private String dictRemark;

    @NotNull(message = "dictStatus参数缺失")
    @IntegerContains(values = {0, 1}, message = "dictStatus参数不在合法值内")
    @ApiModelProperty(value = "状态", required = true)
    private Integer dictStatus;

}
