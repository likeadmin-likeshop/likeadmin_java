package com.mdd.common.entity.setting;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("支付方式实体")
public class DevPayWay implements Serializable {

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("支付配置ID")
    private Integer payConfigId;

    @ApiModelProperty("场景编码: [1=微信小程序, 2=微信公众号, 3=H5, 4=PC, 5=APP]")
    private Integer scene;

    @ApiModelProperty("默认支付: [0=否的, 1=是的]")
    private Integer isDefault;

    @ApiModelProperty("方式状态: [0=关闭, 1=开启]")
    private Integer status;

}
