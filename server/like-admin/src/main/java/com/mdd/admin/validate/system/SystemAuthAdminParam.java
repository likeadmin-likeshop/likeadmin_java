package com.mdd.admin.validate.system;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 系统管理员参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemAuthAdminParam implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface create{}
    public interface update{}
    public interface upInfo{}
    public interface delete{}

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Integer id;

    @NotNull(message = "请选择角色", groups = {create.class, update.class})
    @Min(value = 0, message = "role参数异常", groups = {create.class, update.class})
    private Integer role;

    @NotEmpty(message = "账号不能为空", groups = {create.class, update.class})
    @Length(min = 2, max = 20, message = "账号必须在2~20个字符内", groups = {create.class, update.class})
    private String username;

    @NotEmpty(message = "昵称不能为空", groups = {create.class, update.class, upInfo.class})
    @Length(min = 2, max = 30, message = "昵称必须在2~30个字符内", groups = {create.class, update.class, upInfo.class})
    private String nickname;

    @NotEmpty(message = "密码不能为空", groups = {create.class})
    @Length(min = 6, max = 32, message = "密码必须在6~32个字符内", groups = {create.class})
    private String password;

    @NotEmpty(message = "当前密码不能为空", groups = {upInfo.class})
    @Length(min = 6, max = 32, message = "当前密码错误", groups = {upInfo.class})
    private String currPassword;

    @NotNull(message = "请选择状态", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, message = "isDisable参数不在合法值内", groups = {create.class, update.class})
    private Integer isDisable;

    @NotNull(message = "请选择是否支持多端登录", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, message = "isMultipoint参数不在合法值内", groups = {create.class, update.class})
    private Integer isMultipoint;

    @DecimalMin(value = "0", message = "排序号值不能少于0", groups = {create.class, update.class})
    private Integer sort = 0;

    private Integer deptId = 0;

    private Integer postId = 0;

    private String avatar = "";

}
