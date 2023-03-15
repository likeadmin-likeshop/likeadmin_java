package com.mdd.common.entity.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("文章实体")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("分类")
    private Integer cid;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("简介")
    private String intro;

    @ApiModelProperty("摘要")
    private String summary;

    @ApiModelProperty("封面")
    private String image;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("浏览")
    private Integer visit;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("是否显示: [0=否, 1=是]")
    private Integer isShow;

    @ApiModelProperty("是否删除: [0=否, 1=是]")
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}
