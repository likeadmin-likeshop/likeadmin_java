package com.mdd.admin.service.impl;

import com.mdd.admin.service.ISettingUserService;
import com.mdd.admin.validate.setting.SettingUserValidate;
import com.mdd.admin.vo.setting.SettingUserVo;
import com.mdd.common.util.ConfigUtils;
import com.mdd.common.util.UrlUtils;
import org.springframework.stereotype.Service;

/**
 * 用户设置服务实现类
 */
@Service
public class SettingUserServiceImpl implements ISettingUserService {

    /**
     * 用户设置详情
     *
     * @author fzr
     * @return SettingUserVo
     */
    @Override
    public SettingUserVo detail() {
        String defaultAvatar = ConfigUtils.get("user", "defaultAvatar", "");

        SettingUserVo vo = new SettingUserVo();
        vo.setDefaultAvatar(UrlUtils.toAbsoluteUrl(defaultAvatar));
        return vo;
    }

    /**
     * 用户设置保存
     *
     * @author fzr
     * @param userValidate 参数
     */
    @Override
    public void save(SettingUserValidate userValidate) {
        ConfigUtils.set("user", "defaultAvatar", UrlUtils.toRelativeUrl(userValidate.getDefaultAvatar()));
    }

}
