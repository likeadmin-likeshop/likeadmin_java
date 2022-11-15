package com.mdd.admin.validate.setting;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 字典数据创建参数
 */
@Data
public class DictDataCreateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "typeId参数必传且需大于0")
    private Integer typeId;

    @NotNull(message = "name参数缺失")
    private String name;

    @NotNull(message = "value参数缺失")
    @Length(max = 100, message = "键名不能超出100个字符")
    private String value;

    @Length(max = 200, message = "数值不能超出200个字符")
    private String remark;

    @DecimalMin(value = "0", message = "排序号值不能少于0")
    private Integer sort;

    @NotNull(message = "status参数缺失")
    @IntegerContains(values = {0, 1}, message = "status参数不在合法值内")
    private Integer status;

}
