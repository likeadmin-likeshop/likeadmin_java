package com.mdd.admin.vo.decorate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("装修文章数据Vo")
public class DecorateDataArticleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章简介")
    private String intro;

    @ApiModelProperty(value = "文章描述")
    private String summary;

    @ApiModelProperty(value = "文章图片")
    private String image;

    @ApiModelProperty(value = "文章作者")
    private String author;

    @ApiModelProperty(value = "浏览数量")
    private Integer visit;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

}
