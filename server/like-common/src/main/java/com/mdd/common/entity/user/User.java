package com.mdd.common.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;            // 主键
    private Integer sn;             // 编号
    private String avatar;         // 用户头像
    private String realName;       // 真实姓名
    private String nickname;       // 用户昵称
    private String username;       // 用户账号
    private String password;       // 用户密码
    private String mobile;         // 用户电话
    private Integer channel;       // 注册渠道
    private String salt;           // 加密盐巴
    private Integer sex;           // 用户性别: [1=男, 2=女]
    private Integer is_delete;     // 是否删除: [0=否, 1=是]
    private String lastLoginIp;    // 最后登录IP
    private Long lastLoginTime;    // 最后登录时间
    private Long createTime;       // 创建时间
    private Long updateTime;       // 更新时间
    private Long deleteTime;       // 删除时间

}
