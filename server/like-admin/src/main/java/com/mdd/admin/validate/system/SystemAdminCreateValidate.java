package com.mdd.admin.validate.system;

import com.mdd.common.validator.annotation.IntegerContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("系统管理员创建参数")
public class SystemAdminCreateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "账号不能为空")
    @Length(min = 2, max = 20, message = "账号必须在2~20个字符内")
    @ApiModelProperty(value = "登录账号", required = true)
    private String username;

    @NotEmpty(message = "昵称不能为空")
    @Length(min = 2, max = 30, message = "昵称必须在2~30个字符内")
    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickname;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 32, message = "密码必须在6~32个字符内")
    @ApiModelProperty(value = "登录密码", required = true)
    private String password;

    @NotNull(message = "请选择是否禁用")
    @IntegerContains(values = {0, 1})
    @ApiModelProperty(value = "是否禁用", required = true)
    private Integer isDisable;

    @NotNull(message = "请选择是否支持多端登录")
    @IntegerContains(values = {0, 1})
    @ApiModelProperty(value = "支持多端", required = true)
    private Integer isMultipoint;

    @NotNull(message = "排序号不能为空")
    @DecimalMin(value = "0", message = "排序号值不能少于0")
    @ApiModelProperty(value = "排序", required = true)
    private Integer sort = 0;

    @Length(max = 200, message = "头像不能超出200个字符")
    @ApiModelProperty(value = "头像")
    private String avatar = "";

    @NotNull(message = "请选择角色")
    @ApiModelProperty(value = "角色ID", required = true)
    private List<Integer> roleIds;

    @ApiModelProperty(value = "部门ID")
    private List<Integer> deptIds;

    @ApiModelProperty(value = "岗位ID")
    private List<Integer> postIds;

}
