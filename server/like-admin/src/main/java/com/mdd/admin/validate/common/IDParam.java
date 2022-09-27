package com.mdd.admin.validate.common;

import com.mdd.common.validator.annotation.IDMust;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * ID参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class IDParam {

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

}
