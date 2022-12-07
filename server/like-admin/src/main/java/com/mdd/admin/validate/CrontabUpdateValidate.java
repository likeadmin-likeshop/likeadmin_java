package com.mdd.admin.validate;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CrontabUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotNull(message = "groups参数缺失")
    private String groups;

    @NotNull(message = "name参数缺失")
    private String name;

    @NotNull(message = "command参数缺失")
    private String command;

    @NotNull(message = "rules参数缺失")
    private String rules;

    @Length(max = 200, message = "remark参数不能超出200个字符")
    private String remark;

    @NotNull(message = "status参数缺失")
    @IntegerContains(values = {1, 2, 3}, message = "status参数取值异常")
    private Integer status;

    @NotNull(message = "strategy参数缺失")
    @IntegerContains(values = {1, 2, 3}, message = "strategy参数取值异常")
    private Integer strategy;

    @NotNull(message = "concurrent参数缺失")
    @IntegerContains(values = {0, 1}, message = "concurrent参数取值异常")
    private Integer concurrent;

}
