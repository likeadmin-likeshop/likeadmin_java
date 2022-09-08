package com.mdd.admin.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户Vo
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer sn;
    private String avatar;
    private String realName;
    private String nickname;
    private String username;
    private String mobile;
    private String sex;
    private String channel;
    private String lastLoginIp;
    private String lastLoginTime;
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
