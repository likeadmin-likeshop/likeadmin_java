package com.mdd.common.entity.setting;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("支付配置实体")
public class DevPayConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("模版名称")
    private String name;

    @TableField(exist = false)
    @ApiModelProperty("显示名称")
    private String showName;

    @ApiModelProperty("渠道图标")
    private String icon;

    @ApiModelProperty("支付方式: [1=余额支付, 2=微信支付, 3=支付宝支付]")
    private Integer way;

    @ApiModelProperty("排序编号")
    private Integer sort;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty("配置参数")
    private Object params;

}
