package com.hxkj.admin.validate.system;

import com.hxkj.common.validator.annotation.IDMust;
import com.hxkj.common.validator.annotation.IntegerContains;
import com.hxkj.common.validator.annotation.StringContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统菜单参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemMenuParam implements Serializable {

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

    @Length(max = 200, message = "路由地址不能超过200个字符", groups = {create.class, update.class})
    private String paths;

    @Length(max = 200, message = "前端组件不能超过200个字符", groups = {create.class, update.class})
    private String component;

    @Length(max = 200, message = "选中菜单路径不能超过200个字符", groups = {create.class, update.class})
    private String selected;

    @Length(max = 200, message = "路由参数不能超过200个字符", groups = {create.class, update.class})
    private String params;

    @NotNull(message = "请选择缓存状态", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, groups = {create.class, update.class})
    private Integer isCache;

    @NotNull(message = "请选择显示状态", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, groups = {create.class, update.class})
    private Integer isShow;

    @NotNull(message = "请选择菜单状态", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, groups = {create.class, update.class})
    private Integer isDisable;

}
