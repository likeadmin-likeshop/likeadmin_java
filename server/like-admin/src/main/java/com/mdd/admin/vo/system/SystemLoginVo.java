package com.mdd.admin.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统登录Vo
 */
@Data
public class SystemLoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String token;

}
