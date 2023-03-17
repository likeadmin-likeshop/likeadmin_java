package com.mdd.front.validate.common;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;

@Data
@ApiModel("分页参数")
public class PageValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    // 当前分页
    @DecimalMin(value = "1", message = "pageNo参数必须大于0的数字")
    public Integer pageNo = 1;

    // 每页条数
    @DecimalMin(value = "1", message = "pageSize参数必须是大于0的数字")
    @DecimalMax(value = "60", message = "pageSize参数必须是小于60的数字")
    private Integer pageSize = 20;

}
