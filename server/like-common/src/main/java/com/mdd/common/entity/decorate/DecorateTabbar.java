package com.mdd.common.entity.decorate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("底部导航实体")
public class DecorateTabbar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("导航名称")
    private String name;

    @ApiModelProperty("未选图标")
    private String selected;

    @ApiModelProperty("已选图标")
    private String unselected;

    @ApiModelProperty("链接地址")
    private String link;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

}
