package com.mdd.front.vo.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "文章收藏Vo")
public class ArticleCollectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收藏主键")
    private Integer id;

    @ApiModelProperty(value = "文章ID")
    private Integer articleId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章封面")
    private String image;

    @ApiModelProperty(value = "文章简介")
    private String intro;

    @ApiModelProperty(value = "浏览数量")
    private Integer visit;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

}
