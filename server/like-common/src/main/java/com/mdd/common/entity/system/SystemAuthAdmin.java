package com.mdd.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统管理员实体
 */
@Data
public class SystemAuthAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type=IdType.AUTO)
    private Integer id;            // 主键
    private String nickname;       // 用户账号
    private String username;       // 用户昵称
    private String password;       // 用户密码
    private String avatar;         // 用户头像
    private String salt;           // 加密盐巴
    private String roleIds;        // 角色主键
    private String deptIds;        // 部门主键
    private String postIds;        // 岗位主键
    private Integer sort;          // 排序编号
    private Integer isMultipoint;  // 多端登录: [0=否, 1=是]
    private Integer isDisable;     // 是否禁用: [0=否, 1=是]
    private Integer isDelete;      // 是否删除: [0=否, 1=是]
    private String lastLoginIp;    // 最后登录IP
    private Long lastLoginTime;    // 最后登录时间
    private Long createTime;       // 创建时间
    private Long updateTime;       // 更新时间
    private Long deleteTime;       // 删除时间

}
