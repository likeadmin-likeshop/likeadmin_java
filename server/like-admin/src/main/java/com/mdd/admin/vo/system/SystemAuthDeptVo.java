package com.mdd.admin.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("部门Vo")
public class SystemAuthDeptVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "部门父级")
    private Integer pid;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "负责人")
    private String duty;

    @ApiModelProperty(value = "联系电话")
    private String mobile;

    @ApiModelProperty(value = "排序编号")
    private Integer sort;

    @ApiModelProperty(value = "是否停用: [0=否, 1=是]")
    private Integer isStop;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

}
