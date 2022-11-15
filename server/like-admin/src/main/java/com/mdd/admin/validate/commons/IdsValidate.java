package com.mdd.admin.validate.commons;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * IDS参数
 */
@Data
public class IdsValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ids参数缺失")
    private List<Integer> ids;

}
