package com.mdd.front.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户个人中心Vo
 */
@Data
public class UserInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer sn;
    private String avatar;
    private String realName;
    private String nickname;
    private String username;
    private String mobile;
    private String sex;
    private Boolean isPassword;
    private Boolean isBindMnp;
    private String version;
    private String createTime;

    public void setSex(Integer sex) {
        switch (sex) {
            case 0:
                this.sex = "未知";
                break;
            case 1:
                this.sex = "男";
                break;
            case 2:
                this.sex = "女";
                break;
        }
    }

}
