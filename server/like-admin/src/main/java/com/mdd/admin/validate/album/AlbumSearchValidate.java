package com.mdd.admin.validate.album;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("附件搜索参数")
public class AlbumSearchValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID")
    private Integer cid;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "关键词")
    private String keyword;

}
