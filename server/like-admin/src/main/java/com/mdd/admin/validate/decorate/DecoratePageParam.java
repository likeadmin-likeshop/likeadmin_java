package com.mdd.admin.validate.decorate;

import com.mdd.common.validator.annotation.IDMust;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 页面装修参数类
 */
@Data
public class DecoratePageParam {

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotNull(message = "pageData参数缺失")
    private Object pageData;

}
