package com.mdd.front.vo.login;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统登录Vo
 */
@Data
public class LoginTokenVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Boolean isBindMobile;
    private String token;

}
