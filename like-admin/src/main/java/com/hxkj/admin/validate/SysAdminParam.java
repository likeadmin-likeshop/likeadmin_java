package com.hxkj.admin.validate;

import com.hxkj.common.validator.annotation.IDMust;
import org.hibernate.validator.constraints.Length;

import lombok.Data;
import javax.validation.constraints.*;

/**
 * 系统管理员参数
 */
@Data
public class SysAdminParam {

    public interface create{}
    public interface update{}
    public interface delete{}

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Integer id;

    @NotNull(message = "请选择角色", groups = {create.class, update.class})
    @Min(value = 1, message = "role参数异常", groups = {create.class, update.class})
    private Integer role;

    @NotEmpty(message = "账号不能为空", groups = {create.class, update.class})
    @Length(min = 2, max = 20, message = "账号必须在2~20个字符内", groups = {create.class, update.class})
    private String username;

    @NotEmpty(message = "昵称不能为空", groups = {create.class, update.class})
    @Length(min = 2, max = 30, message = "昵称必须在2~30个字符内", groups = {create.class, update.class})
    private String nickname;

    @NotEmpty(message = "密码不能为空", groups = {create.class, update.class})
    @Length(min = 6, max = 18, message = "密码必须在6~18个字符内", groups = {create.class, update.class})
    private String password;

    @NotNull(message = "请选择状态", groups = {create.class, update.class})
    private Boolean isDisable;

    @NotNull(message = "排序号不能为空", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "排序号值不能少于0", groups = {create.class, update.class})
    private Integer sort;

    private String avatar = "";

}
