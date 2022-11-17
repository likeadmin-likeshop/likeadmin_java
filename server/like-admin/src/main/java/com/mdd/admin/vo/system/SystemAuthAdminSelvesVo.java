package com.mdd.admin.vo.system;

import lombok.Data;

/**
 * 当前系统管理员Vo
 */
@Data
public class SystemAuthAdminSelvesVo {

    private Object user;        // 用户信息
    private Object permissions; // 权限集合: [[*]=>所有权限, ['article:add']=>部分权限]

}
