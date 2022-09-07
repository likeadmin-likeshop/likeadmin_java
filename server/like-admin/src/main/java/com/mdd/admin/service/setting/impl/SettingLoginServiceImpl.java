package com.mdd.admin.service.setting.impl;

import com.mdd.admin.service.setting.ISettingLoginService;
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
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail() {
        Map<String, String> config = ConfigUtil.get("login");
        Map<String, Object> response = new LinkedHashMap<>();
        // 登录方式
        response.put("loginWay", ArrayUtil.stringToListAsInt(config.getOrDefault("loginWay", ""), ","));
        // 强制绑定手机
        response.put("forceBindMobile", Integer.parseInt(config.getOrDefault("forceBindMobile", "0")));
        // 是否开启协议
        response.put("openAgreement", Integer.parseInt(config.getOrDefault("openAgreement", "0")));
        // 第三方的登录
        response.put("openOtherAuth", Integer.parseInt(config.getOrDefault("openOtherAuth", "0")));
        // 自动登录授权
        response.put("autoLoginAuth", ArrayUtil.stringToListAsInt(config.getOrDefault("autoLoginAuth", ""), ","));
        return response;
    }

    /**
     * 登录设置保存
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void save(Map<String, String> params) {
        ConfigUtil.set("login", "loginWay", params.getOrDefault("loginWay", ""));
        ConfigUtil.set("login", "forceBindMobile", params.getOrDefault("forceBindMobile", "0"));
        ConfigUtil.set("login", "openAgreement", params.getOrDefault("openAgreement", "0"));
        ConfigUtil.set("login", "openOtherAuth", params.getOrDefault("openOtherAuth", "0"));
        ConfigUtil.set("login", "autoLoginAuth", params.getOrDefault("autoLoginAuth", ""));
    }

}
