package com.mdd.front.validate.article;

import com.mdd.common.validator.annotation.StringContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@ApiModel("文章搜索参数")
public class ArticleSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cid;

    @Length(max = 100, message = "关键词过长了")
    @ApiModelProperty(value = "关键词")
    private String keyword;

    @StringContains(values = {"hot", "new"})
    @ApiModelProperty(value = "排序号")
    private String sort;

}
