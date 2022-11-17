package com.mdd.admin.validate;

import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 附件分类参数
 */
@Data
public class AlbumCateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "缺少pid参数")
    @Min(value = 0, message = "pid参数必须为数字")
    private Integer pid;

    @NotNull(message = "缺少type参数")
    @IntegerContains(values = {10, 20, 30}, message = "type不在合法值内")
    private Integer type;

    @NotEmpty(message = "名称不能为空")
    @Length(min = 1, max = 30, message = "名称不能大于30个字符")
    private String name;

}
