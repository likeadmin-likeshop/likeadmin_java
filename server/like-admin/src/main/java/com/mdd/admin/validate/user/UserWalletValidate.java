package com.mdd.admin.validate.user;

import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UserWalletValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "缺少用户id参数")
    @Min(value = 0, message = "用户id必须为数字")
    private Integer userId;

    @NotNull(message = "请输入变动类型")
    @IntegerContains(values = {0,1}, message = "变动类型错误")
    private Integer action;

    @NotNull(message = "请输入变动金额")
    private BigDecimal amount;

    private String remark;

}
