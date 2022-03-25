package com.hxkj.admin.validate;

import com.hxkj.common.validator.annotation.IDMust;
import com.hxkj.common.validator.annotation.StringContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 系统菜单参数
 */
@Data
public class SysMenuParam {

    public interface create{}
    public interface update{}
    public interface delete{}

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Integer id;

    @NotNull(message = "上级菜单不能为空", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "上级菜单值不能少于0", groups = {create.class, update.class})
    private Integer pid;

    @NotNull(message = "缺少参数menuType", groups = {create.class, update.class})
    @StringContains(values = {"M", "C", "A"}, message = "菜单类型不是合法值(M,C,A)", groups = {create.class, update.class})
    private String menuType;

    @NotNull(message = "缺少参数menuName", groups = {create.class, update.class})
    @Length(min = 1, max = 30, message = "菜单名称必须在1~30个字符内", groups = {create.class, update.class})
    private String menuName;

    @Length(max = 100, message = "图标名称不能超过100个字符", groups = {create.class, update.class})
    private String menuIcon;

    @NotNull(message = "排序号不能为空", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "排序号值不能少于0", groups = {create.class, update.class})
    private Integer menuSort;

    @Length(max = 100, message = "权限字符不能超过100个字符", groups = {create.class, update.class})
    private String perms;

    @NotNull(message = "请选择状态", groups = {create.class, update.class})
    private Integer isDisable;

}
