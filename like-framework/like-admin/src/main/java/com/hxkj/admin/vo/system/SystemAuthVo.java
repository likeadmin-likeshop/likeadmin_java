package com.hxkj.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限Vo
 */
@Data
public class SystemAuthVo implements Serializable {

    private String path;
    private Object auth;

}
