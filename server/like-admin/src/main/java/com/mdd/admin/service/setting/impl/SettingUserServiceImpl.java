package com.mdd.admin.service.setting.impl;

import com.mdd.admin.service.setting.ISettingUserService;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户设置服务实现类
 */
@Service
public class SettingUserServiceImpl implements ISettingUserService {

    /**
     * 用户设置详情
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail() {
        String defaultAvatar = ConfigUtil.get("user", "defaultAvatar", "");
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("defaultAvatar", UrlUtil.toAbsoluteUrl(defaultAvatar));
        return response;
    }

    /**
     * 用户设置保存
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void save(Map<String, String> params) {
        ConfigUtil.set("user", "defaultAvatar", UrlUtil.toRelativeUrl(params.getOrDefault("defaultAvatar", "")));
    }

}
