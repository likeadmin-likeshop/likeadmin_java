package com.mdd.admin.vo.album;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("相册Vo")
public class AlbumVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "所属类目")
    private Integer cid;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "相对路径")
    private String path;

    @ApiModelProperty(value = "文件路径")
    private String uri;

    @ApiModelProperty(value = "文件扩展")
    private String ext;

    @ApiModelProperty(value = "文件大小")
    private String size;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

}
