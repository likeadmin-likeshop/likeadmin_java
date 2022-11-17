package com.mdd.admin.service.impl;

import com.mdd.admin.service.ISettingUserService;
import com.mdd.admin.validate.setting.SettingUserValidate;
import com.mdd.admin.vo.setting.SettingUserVo;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.UrlUtil;
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
        String defaultAvatar = ConfigUtil.get("user", "defaultAvatar", "");

        SettingUserVo vo = new SettingUserVo();
        vo.setDefaultAvatar(UrlUtil.toAbsoluteUrl(defaultAvatar));
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
        ConfigUtil.set("user", "defaultAvatar", UrlUtil.toRelativeUrl(userValidate.getDefaultAvatar()));
    }

}
