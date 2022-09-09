package com.mdd.admin.validate.setting;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 字典数据参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DictDataParam implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface create{}
    public interface update{}
    public interface delete{}

    @IDMust(message = "id参数必传且需大于0", groups = {update.class})
    private Integer id;

    @NotNull(message = "ids参数缺失", groups = {delete.class})
    private List<Integer> ids;

    @IDMust(message = "typeId参数必传且需大于0", groups = {create.class, update.class})
    private Integer typeId;

    @NotNull(message = "name参数缺失", groups = {create.class, update.class})
    private String name;

    @NotNull(message = "value参数缺失", groups = {create.class, update.class})
    @Length(max = 100, message = "键名不能超出100个字符", groups = {create.class, update.class})
    private String value;

    @Length(max = 200, message = "数值不能超出200个字符", groups = {create.class, update.class})
    private String remark;

    @DecimalMin(value = "0", message = "排序号值不能少于0", groups = {create.class, update.class})
    private Integer sort;

    @NotNull(message = "status参数缺失", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, message = "status参数不在合法值内", groups = {create.class, update.class})
    private Integer status;

}
