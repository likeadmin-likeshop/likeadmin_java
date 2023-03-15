package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统岗位实体")
public class SystemAuthDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("上级部门")
    private Integer pid;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("负责人名")
    private String duty;

    @ApiModelProperty("联系电话")
    private String mobile;

    @ApiModelProperty("排序编号")
    private Integer sort;

    @ApiModelProperty("是否禁用: [0=否, 1=是]")
    private Integer isStop;

    @ApiModelProperty("是否删除: [0=否, 1=是]")
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}
