package com.mdd.common.entity.album;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("相册实体")
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("类目ID")
    private Integer cid;

    @ApiModelProperty("管理ID")
    private Integer aid;

    @ApiModelProperty("用户ID")
    private Integer uid;

    @ApiModelProperty("文件类型: [10=图片, 20=视频]")
    private Integer type;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件路径")
    private String uri;

    @ApiModelProperty("文件扩展")
    private String ext;

    @ApiModelProperty("文件大小")
    private Long size;

    @ApiModelProperty("是否删除: [0=否, 1=是]")
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}
