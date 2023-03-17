package com.mdd.admin.vo.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("公众号关注回复Vo")
public class ChannelRpFollowsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "规则名")
    private String name;

    @ApiModelProperty(value = "回复内容")
    private String content;

    @ApiModelProperty(value = "内容类型: [1=文本]")
    private Integer contentType;

    @ApiModelProperty(value = "排序编号")
    private Integer sort;

    @ApiModelProperty(value = "启动状态: [1=启动, 0=关闭]")
    private Integer status;

}
