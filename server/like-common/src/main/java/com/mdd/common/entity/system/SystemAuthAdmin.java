package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("系统管理员实体")
public class SystemAuthAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type=IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("用户账号")
    private String nickname;

    @ApiModelProperty("用户昵称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("加密盐巴")
    private String salt;

    @ApiModelProperty("角色主键")
    private String roleIds;

    @ApiModelProperty("部门主键")
    private String deptIds;

    @ApiModelProperty("岗位主键")
    private String postIds;

    @ApiModelProperty("排序编号")
    private Integer sort;

    @ApiModelProperty("多端登录: [0=否, 1=是]")
    private Integer isMultipoint;

    @ApiModelProperty("是否禁用: [0=否, 1=是]")
    private Integer isDisable;

    @ApiModelProperty("是否删除: [0=否, 1=是]")
    private Integer isDelete;

    @ApiModelProperty("最后登录IP")
    private String lastLoginIp;

    @ApiModelProperty("最后登录时间")
    private Long lastLoginTime;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("删除时间")
    private Long deleteTime;

}
