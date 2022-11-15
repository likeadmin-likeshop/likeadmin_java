package com.mdd.admin.validate;

import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章分类创建参数
 */
@Data
public class ArtCateCreateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "分类名称不能为空")
    @Length(min = 1, max = 60, message = "分类名称不能大于60个字符")
    private String name;

    @NotNull(message = "排序号不能为空")
    @DecimalMin(value = "0", message = "排序号值不能少于0")
    private Integer sort;

    @NotNull(message = "缺少isShow参数")
    @IntegerContains(values = {0, 1}, message = "isShow不是合法值")
    private Integer isShow;

}
