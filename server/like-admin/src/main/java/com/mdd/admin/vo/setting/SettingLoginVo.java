package com.mdd.admin.vo.setting;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 登录设置Vo
 */
@Data
public class SettingLoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Integer> loginWay;       // 登录方式
    private Integer forceBindMobile;      // 强制绑定手机
    private Integer openAgreement;        // 是否开启协议
    private Integer openOtherAuth;        // 第三方的登录
    private List<Integer> autoLoginAuth;  // 自动登录授权

}
