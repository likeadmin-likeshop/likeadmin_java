package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统登录日志实体")
public class SystemLogLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("管理员ID")
    private Integer adminId;

    @ApiModelProperty("登录账号")
    private String username;

    @ApiModelProperty("登录IP")
    private String ip;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("操作状态: [1=成功, 2=失败]")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Long createTime;

}
