package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统角色实体")
public class SystemAuthRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty("角色排序")
    private Integer sort;

    @ApiModelProperty("是否禁用: [0=否, 1=是]")
    private Integer isDisable;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;   

}
