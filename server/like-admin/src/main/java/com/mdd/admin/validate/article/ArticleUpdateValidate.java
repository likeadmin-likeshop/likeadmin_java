package com.mdd.admin.validate.article;

import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IntegerContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("文章更新参数")
public class ArticleUpdateValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @IDMust(message = "id参数必传且需大于0")
    @ApiModelProperty(value = "id", required = true)
    private Integer id;

    @IDMust(message = "cid参数必传且需大于0")
    @ApiModelProperty(value = "分类ID", required = true)
    private Integer cid;

    @NotEmpty(message = "文章标题不能为空")
    @Length(min = 1, max = 200, message = "文章标题不能大于200个字符")
    @ApiModelProperty(value = "文章标题", required = true)
    private String title;

    @Length(max = 200, message = "简介不能超出200个字符")
    @ApiModelProperty(value = "文章简介")
    private String intro = "";

    @Length(max = 200, message = "图片链接过长不能超200个字符")
    @ApiModelProperty(value = "封面图片")
    private String image = "";

    @Length(max = 32, message = "作者名称不能超32个字符")
    @ApiModelProperty(value = "作者姓名")
    private String author = "";

    @NotNull(message = "排序号不能为空")
    @DecimalMin(value = "0", message = "排序号值不能少于0")
    @ApiModelProperty(value = "排序", required = true)
    private Integer sort;

    @NotNull(message = "缺少isShow参数")
    @IntegerContains(values = {0, 1}, message = "isShow不是合法值")
    @ApiModelProperty(value = "是否显示", required = true)
    private Integer isShow;

    @ApiModelProperty(value = "文章内容")
    private String content = "";

    @ApiModelProperty(value = "文章描述")
    private String summary = "";

    @DecimalMin(value = "0", message = "初始浏览量不能少于0")
    @ApiModelProperty(value = "浏览数量")
    private Integer visit = 0;

}
