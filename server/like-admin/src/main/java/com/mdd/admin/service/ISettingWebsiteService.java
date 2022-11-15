package com.mdd.admin.service;

import com.mdd.admin.validate.setting.SettingWebsiteValidate;
import com.mdd.admin.vo.setting.SettingWebsiteVo;

import java.util.Map;

/**
 * 网站信息服务接口类
 */
public interface ISettingWebsiteService {

    /**
     * 获取网站信息
     *
     * @author fzr
     * @return SettingWebsiteVo
     */
    SettingWebsiteVo detail();

    /**
     * 保存网站信息
     *
     * @author fzr
     * @param websiteValidate 参数
     */
    void save(SettingWebsiteValidate websiteValidate);

}
