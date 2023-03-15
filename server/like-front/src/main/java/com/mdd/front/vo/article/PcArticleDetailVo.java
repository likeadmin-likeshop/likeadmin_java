package com.mdd.front.vo.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "PC文章详情Vo")
public class PcArticleDetailVo implements Serializable {

    @ApiModelProperty(value = "文章ID")
    private Integer id;

    @ApiModelProperty(value = "分类ID")
    private Integer cid;

    @ApiModelProperty(value = "分类名称")
    private String category;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章简介")
    private String intro;

    @ApiModelProperty(value = "文章描述")
    private String summary;

    @ApiModelProperty(value = "文章封面")
    private String image;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章作者")
    private String author;

    @ApiModelProperty(value = "浏览数量")
    private Integer visit;

    @ApiModelProperty(value = "排序编号")
    private Integer sort;

    @ApiModelProperty(value = "是否收藏")
    private Integer isCollect;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "上一页")
    private Object prev;

    @ApiModelProperty(value = "下一页")
    private Object next;

    @ApiModelProperty(value = "最新推荐")
    private Object news;

}
