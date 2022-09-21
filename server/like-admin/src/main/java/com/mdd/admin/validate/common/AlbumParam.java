package com.mdd.admin.validate.common;

import com.mdd.admin.validate.BaseParam;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 相册参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AlbumParam extends BaseParam {

    public interface rename{}
    public interface cateAdd{}
    public interface albumMove{}

    @IDMust(message = "id参数必传且需大于0", groups = {rename.class, albumMove.class, delete.class})
    private Integer id;

    @IDMust(message = "id参数必传且需大于0", groups = {albumMove.class})
    private Integer cid;

    @NotNull(message = "缺少pid参数",  groups = {cateAdd.class})
    @Min(value = 0, message = "pid参数必须为数字",  groups = {cateAdd.class})
    private Integer pid;

    @NotNull(message = "缺少type参数", groups = {cateAdd.class})
    @IntegerContains(values = {10, 20, 30}, message = "type不在合法值内", groups = {cateAdd.class})
    private Integer type;

    @NotEmpty(message = "名称不能为空", groups = {rename.class})
    @Length(min = 1, max = 30, message = "名称不能大于30个字符", groups = {rename.class})
    private String name;

}
