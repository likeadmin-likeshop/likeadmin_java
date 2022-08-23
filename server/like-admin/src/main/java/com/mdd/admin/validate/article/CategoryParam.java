package com.mdd.admin.validate.article;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章分类参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CategoryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface create{}
    public interface update{}
    public interface delete{}
    public interface change{}

    @IDMust(message = "id参数必传且需大于0", groups = {create.class, delete.class, change.class})
    private Integer id;

    @NotEmpty(message = "分类名称不能为空", groups = {create.class, update.class})
    @Length(min = 1, max = 60, message = "分类名称不能大于60个字符", groups = {create.class, update.class})
    private String name;

    @NotNull(message = "排序号不能为空", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "排序号值不能少于0", groups = {create.class, update.class})
    private Integer sort;

    @NotNull(message = "缺少isShow参数", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, message = "isShow不是合法值", groups = {create.class, update.class})
    private Integer isShow;

}
