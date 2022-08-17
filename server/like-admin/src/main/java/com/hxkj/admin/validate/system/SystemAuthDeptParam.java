package com.hxkj.admin.validate.system;

import com.hxkj.common.validator.annotation.IDMust;
import com.hxkj.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 系统部门参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemAuthDeptParam {

    public interface create{}
    public interface update{}
    public interface delete{}

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Integer id;

    @NotNull(message = "pid参数缺失", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "上级值不能少于0", groups = {create.class, update.class})
    private Integer pid;

    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(min = 1, max = 100, message = "部门名称必须在1~100个字符内", groups = {create.class, update.class})
    private String name;

    @Length(min = 1, max = 30, message = "负责人名称必须在1~30个字符内", groups = {create.class, update.class})
    private String duty = "";

    @Length(min = 11, max = 11, message = "手机号只能为11位", groups = {create.class, update.class})
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误", groups = {create.class, update.class})
    private String mobile = "";

    @NotNull(message = "请选择状态", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, groups = {create.class, update.class})
    private Integer isStop = 0;

    private Integer sort = 0;
}
