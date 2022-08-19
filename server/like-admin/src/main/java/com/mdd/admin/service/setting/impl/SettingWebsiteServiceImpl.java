package com.mdd.admin.service.setting.impl;

import com.mdd.admin.service.setting.ISettingWebsiteService;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 网站信息配置服务实现类
 */
@Service
public class SettingWebsiteServiceImpl implements ISettingWebsiteService {

    /**
     * 获取网站信息
     *
     * @author fzr
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> detail() {
        Map<String, String> config = ConfigUtil.get("website");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", config.getOrDefault("name", ""));
        map.put("logo", UrlUtil.toAbsoluteUrl(config.getOrDefault("logo", "")));
        map.put("favicon", UrlUtil.toAbsoluteUrl(config.getOrDefault("favicon", "")));
        map.put("backdrop", UrlUtil.toAbsoluteUrl(config.getOrDefault("backdrop", "")));
        return map;
    }

    /**
     * 保存网站信息
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void save(Map<String, String> params) {
        ConfigUtil.set("website", "name", params.getOrDefault("name", ""));
        ConfigUtil.set("website", "logo", UrlUtil.toRelativeUrl(params.getOrDefault("logo", "")));
        ConfigUtil.set("website", "favicon", UrlUtil.toRelativeUrl(params.getOrDefault("favicon", "")));
        ConfigUtil.set("website", "backdrop", UrlUtil.toRelativeUrl(params.getOrDefault("backdrop", "")));
    }

}
