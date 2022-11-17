package com.mdd.front.vo.users;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户个人中心Vo
 */
@Data
public class UserCenterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer sn;
    private String avatar;
    private String realName;
    private String nickname;
    private String username;
    private String mobile;

}
