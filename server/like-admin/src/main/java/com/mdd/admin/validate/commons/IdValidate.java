package com.mdd.admin.validate.commons;

import com.mdd.common.validator.annotation.IDMust;
import lombok.Data;

import java.io.Serializable;

/**
 * ID参数
 */
@Data
public class IdValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

}
