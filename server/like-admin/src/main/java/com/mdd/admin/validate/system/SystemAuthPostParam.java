package com.mdd.admin.validate.system;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 系统岗位Vo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemAuthPostParam {

    public interface create{}
    public interface update{}
    public interface delete{}

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Integer id;

    @NotNull(message = "code参数缺失", groups = {create.class, update.class})
    @Length(min = 1, max = 30, message = "岗位编码必须在1~30个字符内", groups = {create.class, update.class})
    private String code;

    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    @Length(min = 1, max = 30, message = "岗位名称必须在1~30个字符内", groups = {create.class, update.class})
    private String name;

    @NotNull(message = "请选择状态", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, groups = {create.class, update.class})
    private Integer isStop = 0;

    @Length( max = 250, message = "岗位备注不能大于250个字符内", groups = {create.class, update.class})
    private String remarks = "";
    @NotNull(message = "排序号不能为空", groups = {SystemAuthRoleParam.create.class, SystemAuthRoleParam.update.class})
    @DecimalMin(value = "0", message = "排序号值不能少于0", groups = {SystemAuthAdminParam.create.class, SystemAuthAdminParam.update.class})
    private Integer sort = 0;

}
