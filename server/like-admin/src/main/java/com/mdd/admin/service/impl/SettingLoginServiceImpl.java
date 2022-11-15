package com.mdd.admin.service.impl;

import com.mdd.admin.service.ISettingLoginService;
import com.mdd.admin.validate.setting.SettingLoginValidate;
import com.mdd.admin.vo.setting.SettingLoginVo;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.ConfigUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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
        Map<String, String> config = ConfigUtil.get("login");

        SettingLoginVo vo =  new SettingLoginVo();
        vo.setLoginWay(ArrayUtil.stringToListAsInt(config.getOrDefault("loginWay", ""), ","));
        vo.setForceBindMobile(Integer.parseInt(config.getOrDefault("forceBindMobile", "0")));
        vo.setOpenAgreement(Integer.parseInt(config.getOrDefault("openAgreement", "0")));
        vo.setOpenOtherAuth(Integer.parseInt(config.getOrDefault("openOtherAuth", "0")));
        vo.setAutoLoginAuth(ArrayUtil.stringToListAsInt(config.getOrDefault("autoLoginAuth", ""), ","));
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
        ConfigUtil.set("login", "loginWay", loginValidate.getLoginWay());
        ConfigUtil.set("login", "forceBindMobile", String.valueOf(loginValidate.getForceBindMobile()));
        ConfigUtil.set("login", "openAgreement", String.valueOf(loginValidate.getOpenAgreement()));
        ConfigUtil.set("login", "openOtherAuth", String.valueOf(loginValidate.getOpenOtherAuth()));
        ConfigUtil.set("login", "autoLoginAuth", String.valueOf(loginValidate.getAutoLoginAuth()));
    }

}
