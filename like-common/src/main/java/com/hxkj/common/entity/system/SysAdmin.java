package com.hxkj.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统管理员实体
 */
@Data
public class SysAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type=IdType.AUTO)
    private Integer id;
    private String nickname;
    private String username;
    private String password;
    private String avatar;
    private String salt;
    private Integer role;
    private Integer sort;
    private Boolean isDisable;
    private Boolean isDelete;
    private String lastLoginIp;
    private Long lastLoginTime;
    private Long createTime;
    private Long updateTime;
    private Long deleteTime;

}
