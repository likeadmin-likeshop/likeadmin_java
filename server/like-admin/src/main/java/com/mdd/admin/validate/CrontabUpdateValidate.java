package com.mdd.admin.validate;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CrontabUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotNull(message = "name参数缺失")
    private String name;

    @NotNull(message = "command参数缺失")
    private String command;

    @NotNull(message = "rules参数缺失")
    private String rules;

    @IntegerContains(values = {1, 2, 3}, message = "status参数取值异常")
    private Integer status;

    private String remark;

}
