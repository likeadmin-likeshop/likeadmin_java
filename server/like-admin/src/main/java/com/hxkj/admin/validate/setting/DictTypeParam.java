package com.hxkj.admin.validate.setting;

import com.hxkj.common.validator.annotation.IDMust;
import com.hxkj.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 字典类型参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DictTypeParam implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface create{}
    public interface update{}
    public interface delete{}

    @IDMust(message = "id参数必传且需大于0", groups = {update.class})
    private Integer id;

    @NotNull(message = "ids参数缺失", groups = {delete.class})
    private List<Integer> ids;

    @NotNull(message = "dictName参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "名称不能超出200个字符", groups = {create.class, update.class})
    private String dictName;

    @NotNull(message = "dictType参数缺失", groups = {create.class, update.class})
    @Length(max = 200, message = "类型不能超出200个字符", groups = {create.class, update.class})
    private String dictType;

    @Length(max = 200, message = "备注不能超出200个字符", groups = {create.class, update.class})
    private String dictRemark;

    @NotNull(message = "dictStatus参数缺失")
    @IntegerContains(values = {0, 1}, message = "dictStatus参数不在合法值内", groups = {create.class, update.class})
    private Integer dictStatus;

}
