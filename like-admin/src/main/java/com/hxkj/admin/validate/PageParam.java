package com.hxkj.admin.validate;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * 分页参数
 */
@Data
public class PageParam {

    // 当前分页
    @DecimalMin(value = "1", message = "pageNo参数必须大于0的数字")
    public Integer pageNo = 1;

    // 每页条数
    @DecimalMin(value = "1", message = "pageSize参数必须是大于0的数字")
    @DecimalMax(value = "60", message = "pageSize参数必须是小于60的数字")
    private Integer pageSize = 20;

}
