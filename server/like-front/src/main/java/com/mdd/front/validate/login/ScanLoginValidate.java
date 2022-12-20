package com.mdd.front.validate.login;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 网站扫码登录验证
 */
@Data
public class ScanLoginValidate  implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "code参数缺失")
    private String code;

    @NotNull(message = "state参数缺失")
    private String state;

}
