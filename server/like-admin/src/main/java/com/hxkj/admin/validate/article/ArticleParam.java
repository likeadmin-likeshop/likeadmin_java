package com.hxkj.admin.validate.article;

import com.hxkj.admin.validate.system.SystemAdminParam;
import com.hxkj.common.validator.annotation.IDMust;
import com.hxkj.common.validator.annotation.IntegerContains;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ArticleParam implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface create{}
    public interface update{}
    public interface delete{}

    @IDMust(message = "id参数必传且需大于0", groups = {update.class, delete.class})
    private Integer id;

    @IDMust(message = "id参数必传且需大于0", groups = {create.class, update.class})
    private Integer cid;

    @NotEmpty(message = "文章标题不能为空", groups = {create.class, update.class})
    @Length(min = 1, max = 200, message = "文章标题不能大于200个字符", groups = {create.class, update.class})
    private String title;

    @Length(max = 200, message = "简介不能超出200个字符", groups = {create.class, update.class})
    private String intro = "";

    @Length(max = 200, message = "图片链接过长不能超200个字符", groups = {create.class, update.class})
    private String image = "";

    private String content = "";

    @NotNull(message = "排序号不能为空", groups = {create.class, update.class})
    @DecimalMin(value = "0", message = "排序号值不能少于0", groups = {create.class, update.class})
    private Integer sort;

    @NotNull(message = "缺少isShow参数", groups = {create.class, update.class})
    @IntegerContains(values = {0, 1}, message = "isShow不是合法值", groups = {create.class, update.class})
    private Integer isShow;

}
