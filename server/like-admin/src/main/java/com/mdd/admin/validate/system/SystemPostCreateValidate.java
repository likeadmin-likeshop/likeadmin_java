package com.mdd.admin.validate.system;

import com.mdd.common.validator.annotation.IntegerContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("系统岗位创建参数")
public class SystemPostCreateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "code参数缺失")
    @Length(min = 1, max = 30, message = "岗位编码必须在1~30个字符内")
    @ApiModelProperty(value = "编码", required = true)
    private String code;

    @NotNull(message = "name参数缺失")
    @Length(min = 1, max = 30, message = "岗位名称必须在1~30个字符内")
    @ApiModelProperty(value = "岗位名称", required = true)
    private String name;

    @NotNull(message = "请选择状态")
    @IntegerContains(values = {0, 1})
    @ApiModelProperty(value = "是否停用", required = true)
    private Integer isStop = 0;

    @Length( max = 250, message = "岗位备注不能大于250个字符内")
    @ApiModelProperty(value = "备注信息")
    private String remarks = "";

    @NotNull(message = "排序号不能为空")
    @DecimalMin(value = "0", message = "排序号值不能少于0")
    @ApiModelProperty(value = "排序", required = true)
    private Integer sort = 0;

}
