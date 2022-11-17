package com.mdd.admin.service;

import com.mdd.admin.validate.setting.SettingUserValidate;
import com.mdd.admin.vo.setting.SettingUserVo;

import java.util.Map;

/**
 * 用户设置服务接口类
 */
public interface ISettingUserService {

    /**
     * 用户设置详情
     *
     * @author fzr
     * @return SettingUserVo
     */
    SettingUserVo detail();

    /**
     * 用户设置保存
     *
     * @author fzr
     * @param userValidate 参数
     */
    void save(SettingUserValidate userValidate);

}
