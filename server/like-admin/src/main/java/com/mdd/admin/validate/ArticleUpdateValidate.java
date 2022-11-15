package com.mdd.admin.validate;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文章更新参数
 */
@Data
public class ArticleUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    private Integer id;

    @IDMust(message = "cid参数必传且需大于0")
    private Integer cid;

    @NotEmpty(message = "文章标题不能为空")
    @Length(min = 1, max = 200, message = "文章标题不能大于200个字符")
    private String title;

    @Length(max = 200, message = "简介不能超出200个字符")
    private String intro = "";

    @Length(max = 200, message = "图片链接过长不能超200个字符")
    private String image = "";

    @Length(max = 32, message = "作者名称不能超32个字符")
    private String author = "";

    @NotNull(message = "排序号不能为空")
    @DecimalMin(value = "0", message = "排序号值不能少于0")
    private Integer sort;

    @NotNull(message = "缺少isShow参数")
    @IntegerContains(values = {0, 1}, message = "isShow不是合法值")
    private Integer isShow;

    private String content = "";

    private String summary = "";

    private Integer visit = 0;

}
