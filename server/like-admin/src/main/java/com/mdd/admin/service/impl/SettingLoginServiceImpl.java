package com.mdd.admin.service.impl;

import com.mdd.admin.service.ISettingLoginService;
import com.mdd.admin.validate.setting.SettingLoginValidate;
import com.mdd.admin.vo.setting.SettingLoginVo;
import com.mdd.common.util.ListUtils;
import com.mdd.common.util.ConfigUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 登录设置服务接口类
 */
@Service
public class SettingLoginServiceImpl implements ISettingLoginService {

    /**
     * 登录设置详情
     *
     * @author fzr
     * @return SettingLoginVo
     */
    @Override
    public SettingLoginVo detail() {
        Map<String, String> config = ConfigUtils.get("login");

        SettingLoginVo vo =  new SettingLoginVo();
        vo.setLoginWay(ListUtils.stringToListAsInt(config.getOrDefault("loginWay", ""), ","));
        vo.setForceBindMobile(Integer.parseInt(config.getOrDefault("forceBindMobile", "0")));
        vo.setOpenAgreement(Integer.parseInt(config.getOrDefault("openAgreement", "0")));
        vo.setOpenOtherAuth(Integer.parseInt(config.getOrDefault("openOtherAuth", "0")));
        vo.setAutoLoginAuth(ListUtils.stringToListAsInt(config.getOrDefault("autoLoginAuth", ""), ","));
        return vo;
    }

    /**
     * 登录设置保存
     *
     * @author fzr
     * @param loginValidate 参数
     */
    @Override
    public void save(SettingLoginValidate loginValidate) {
        ConfigUtils.set("login", "loginWay", loginValidate.getLoginWay());
        ConfigUtils.set("login", "forceBindMobile", String.valueOf(loginValidate.getForceBindMobile()));
        ConfigUtils.set("login", "openAgreement", String.valueOf(loginValidate.getOpenAgreement()));
        ConfigUtils.set("login", "openOtherAuth", String.valueOf(loginValidate.getOpenOtherAuth()));
        ConfigUtils.set("login", "autoLoginAuth", String.valueOf(loginValidate.getAutoLoginAuth()));
    }

}
