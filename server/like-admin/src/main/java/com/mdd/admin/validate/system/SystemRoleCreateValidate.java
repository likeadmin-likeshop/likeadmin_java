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
@ApiModel("系统角色创建参数")
public class SystemRoleCreateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "缺少参数name")
    @Length(min = 1, max = 30, message = "角色名称必须在1~30个字符内")
    @ApiModelProperty(value = "角色名称", required = true)
    private String name;

    @Length(max = 200, message = "备注信息不能超过200个字符")
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    @DecimalMin(value = "0", message = "排序号值不能少于0")
    @ApiModelProperty(value = "排序", required = true)
    private Integer sort = 0;

    @NotNull(message = "请选择状态")
    @IntegerContains(values = {0, 1})
    @ApiModelProperty(value = "是否禁用", required = true)
    private Integer isDisable;

    @ApiModelProperty(value = "菜单权限")
    private String menuIds = "";

}
