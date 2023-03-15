package com.mdd.common.entity.notice;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("通知记录实体")
public class NoticeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("场景")
    private Integer scene;

    @ApiModelProperty("用户")
    private Integer userId;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("编码")
    private String title;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("错误")
    private String error;

    @ApiModelProperty("发送类型: [1=系统, 2=短信, 3=公众号, 4=小程序]")
    private Integer sender;

    @ApiModelProperty("接收对象: [1=用户, 2=平台]")
    private Integer receiver;

    @ApiModelProperty("通知状态: [0=等待, 1=成功, 2=失败]")
    private Integer status;

    @ApiModelProperty("已读状态: [0=未读, 1=已读]")
    private Integer isRead;

    @ApiModelProperty("是验证码: [0=否的, 1=是的]")
    private Integer isCaptcha;

    @ApiModelProperty("是否删除: [0=否,1=是]")
    private Integer isDelete;

    @ApiModelProperty("失效时间")
    private Long expireTime;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}
