package com.mdd.front.vo.login;

import lombok.Data;

import java.io.Serializable;

/**
 * 公众号跳转url
 */
@Data
public class LoginUrlsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String url;

}
