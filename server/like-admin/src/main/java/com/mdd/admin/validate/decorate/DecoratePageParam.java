package com.mdd.admin.validate.decorate;

import com.mdd.admin.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 页面装修参数类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DecoratePageParam extends BaseParam {

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @NotNull(message = "pageData参数缺失")
    private String pageData;

}
