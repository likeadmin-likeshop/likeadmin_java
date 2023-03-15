package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("公众号回复实体")
public class OfficialReply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("规则名")
    private String name;

    @ApiModelProperty("关键词")
    private String keyword;

    @ApiModelProperty("回复类型: [1=关注回复 2=关键字回复, 3=默认回复]")
    private Integer replyType;

    @ApiModelProperty("匹配方式: [1=全匹配, 2=模糊匹配]")
    private Integer matchingType;

    @ApiModelProperty("内容类型: [1=文本]")
    private Integer contentType;

    @ApiModelProperty("启动状态: [1=启动, 0=关闭]")
    private Integer status;

    @ApiModelProperty("回复内容")
    private String content;

    @ApiModelProperty("排序编号")
    private Integer sort;

    @ApiModelProperty("是否删除")
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}
