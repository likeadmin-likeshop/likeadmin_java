package com.mdd.admin.validate;


import com.mdd.common.validator.annotation.IDMust;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 页面装修参数类
 */
@Data
public class DecoratePageValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotNull(message = "pageData参数缺失")
    private String pageData;

}
