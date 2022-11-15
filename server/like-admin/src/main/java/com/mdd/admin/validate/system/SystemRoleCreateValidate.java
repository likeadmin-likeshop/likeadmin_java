package com.mdd.admin.validate.system;

import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统角色创建参数
 */
@Data
public class SystemRoleCreateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "缺少参数name")
    @Length(min = 1, max = 30, message = "角色名称必须在1~30个字符内")
    private String name;

    @Length(max = 200, message = "备注信息不能超过200个字符")
    private String remark;

    @NotNull(message = "排序号不能为空")
    @DecimalMin(value = "0", message = "排序号值不能少于0")
    private Integer sort = 0;

    @NotNull(message = "请选择状态")
    @IntegerContains(values = {0, 1})
    private Integer isDisable;

    private String menuIds = "";

}
