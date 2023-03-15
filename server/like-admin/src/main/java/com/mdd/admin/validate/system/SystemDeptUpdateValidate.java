package com.mdd.admin.validate.system;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@ApiModel("系统部门更新参数")
public class SystemDeptUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    @ApiModelProperty(value = "id", required = true)
    private Integer id;

    @NotNull(message = "pid参数缺失")
    @DecimalMin(value = "0", message = "上级值不能少于0")
    @ApiModelProperty(value = "上级ID", required = true)
    private Integer pid;

    @NotNull(message = "name参数缺失")
    @Length(min = 1, max = 100, message = "部门名称必须在1~100个字符内")
    @ApiModelProperty(value = "部门名称")
    private String name;

    @Length(min = 1, max = 30, message = "负责人名称必须在1~30个字符内")
    private String duty = "";

    @Length(min = 11, max = 11, message = "手机号只能为11位")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @NotNull(message = "请选择状态")
    @IntegerContains(values = {0, 1})
    @ApiModelProperty(value = "状态")
    private Integer isStop;

    @DecimalMin(value = "0", message = "排序号值不能少于0")
    @DecimalMax(value = "9999", message = "排序号值不能大于9999")
    @ApiModelProperty(value = "排序")
    private Integer sort;

}
