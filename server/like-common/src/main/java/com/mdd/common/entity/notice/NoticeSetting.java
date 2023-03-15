package com.mdd.common.entity.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("通知设置实体")
public class NoticeSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("场景编号")
    private Integer scene;

    @ApiModelProperty("场景名称")
    private String name;

    @ApiModelProperty("场景描述")
    private String remarks;

    @ApiModelProperty("接收人员: [1=用户, 2=平台]")
    private Integer recipient;

    @ApiModelProperty("通知类型: [1=业务, 2=验证码]")
    private Integer type;

    @ApiModelProperty("系统的通知设置")
    private String systemNotice;

    @ApiModelProperty("短信的通知设置")
    private String smsNotice;

    @ApiModelProperty("公众号通知设置")
    private String oaNotice;

    @ApiModelProperty("小程序通知设置")
    private String mnpNotice;

    @ApiModelProperty("是否删除: [0=否, 1=是]")
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}
