package com.mdd.front.validate.login;

import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 公众号登录参数
 */
@Data
public class OaLoginValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "code参数缺失")
    private String code;

    @IntegerContains(values = {1, 2, 3, 4, 5, 6}, message = "client参数值不符合")
    private Integer client;

}
