package com.mdd.admin.service;

import com.mdd.admin.validate.setting.SettingLoginValidate;
import com.mdd.admin.vo.setting.SettingLoginVo;

import java.util.Map;

/**
 * 登录设置服务接口类
 */
public interface ISettingLoginService {

    /**
     * 登录设置详情
     *
     * @author fzr
     * @return SettingLoginVo
     */
    SettingLoginVo detail();

    /**
     * 登录设置保存
     *
     * @author fzr
     * @param loginValidate 参数
     */
    void save(SettingLoginValidate loginValidate);

}
