package com.mdd.admin.validate.system;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 系统管理员更新参数
 */
@Data
public class SystemAdminUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotEmpty(message = "账号不能为空")
    @Length(min = 2, max = 20, message = "账号必须在2~20个字符内")
    private String username;

    @NotEmpty(message = "昵称不能为空")
    @Length(min = 2, max = 30, message = "昵称必须在2~30个字符内")
    private String nickname;

    private String password;

    @NotNull(message = "请选择是否禁用")
    @IntegerContains(values = {0, 1})
    private Integer isDisable;

    @NotNull(message = "请选择是否支持多端登录")
    @IntegerContains(values = {0, 1})
    private Integer isMultipoint;

    @NotNull(message = "排序号不能为空")
    @DecimalMin(value = "0", message = "排序号值不能少于0")
    private Integer sort = 0;

    @Length(max = 200, message = "头像不能超出200个字符")
    private String avatar = "";

    @NotNull(message = "请选择角色")

    private List<Integer> roleIds;

    private List<Integer> deptIds;

    private List<Integer> postIds;

}
