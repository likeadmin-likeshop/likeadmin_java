package com.mdd.front.validate;

import com.mdd.common.validator.annotation.StringContains;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户更新参数
 */
@Data
public class UserUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "field参数缺失")
    @StringContains(values = {"avatar", "username", "nickname", "sex"})
    private String field;

    @NotNull(message = "value参数缺失")
    private String value;

}
