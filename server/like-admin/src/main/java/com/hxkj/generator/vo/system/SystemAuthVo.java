package com.hxkj.generator.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限Vo
 */
@Data
public class SystemAuthVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String path;
    private Object auth;

}
