package com.mdd.admin.validate.crontab;

import com.mdd.common.validator.annotation.IntegerContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("计划任务创建参数")
public class CrontabCreateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "name参数缺失")
    @ApiModelProperty(value = "任务名称", required = true)
    private String name;

    @NotNull(message = "types参数缺失")
    @ApiModelProperty(value = "任务分组", required = true)
    private String types;

    @NotNull(message = "command参数缺失")
    @ApiModelProperty(value = "执行指令", required = true)
    private String command;

    @NotNull(message = "rules参数缺失")
    @ApiModelProperty(value = "执行规则", required = true)
    private String rules;

    @Length(max = 200, message = "remark参数不能超出200个字符")
    @ApiModelProperty(value = "备注")
    private String remark;

    @NotNull(message = "status参数缺失")
    @IntegerContains(values = {1, 2, 3}, message = "status参数取值异常")
    @ApiModelProperty(value = "状态")
    private Integer status;

    @NotNull(message = "strategy参数缺失")
    @IntegerContains(values = {1, 2, 3}, message = "strategy参数取值异常")
    @ApiModelProperty(value = "strategy", required = true)
    private Integer strategy;

    @NotNull(message = "concurrent参数缺失")
    @IntegerContains(values = {0, 1}, message = "concurrent参数取值异常")
    @ApiModelProperty(value = "concurrent", required = true)
    private Integer concurrent;

}
