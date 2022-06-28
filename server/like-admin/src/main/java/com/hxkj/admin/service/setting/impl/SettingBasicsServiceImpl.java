package com.hxkj.admin.service.setting.impl;

import com.hxkj.admin.service.setting.ISettingBasicsService;
import com.hxkj.common.utils.ConfigUtil;
import com.hxkj.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基础配置服务实现类
 */
@Service
public class SettingBasicsServiceImpl implements ISettingBasicsService {

    /**
     * 获取网站信息
     *
     * @author fzr
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> getWebsite() {
        Map<String, String> config = ConfigUtil.get("website");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", config.getOrDefault("name", ""));
        map.put("logo", UrlUtil.toAbsoluteUrl(config.getOrDefault("logo", "")));
        map.put("favicon", UrlUtil.toAbsoluteUrl(config.getOrDefault("favicon", "")));
        map.put("backdrop", UrlUtil.toAbsoluteUrl(config.getOrDefault("backdrop", "")));
        return map;
    }

    /**
     * 获取版权信息
     *
     * @author fzr
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> getCopyright() {
        Map<String, String> config = ConfigUtil.get("copyright");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("privilege", config.getOrDefault("privilege", ""));
        map.put("icpNumber", config.getOrDefault("icpNumber", ""));
        map.put("icpLink", config.getOrDefault("icpLink", ""));
        map.put("gaNumber", config.getOrDefault("gaNumber", ""));
        map.put("gaLink", config.getOrDefault("gaLink", ""));
        return map;
    }

    /**
     * 设置网站信息
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void setWebsite(Map<String, String> params) {
        ConfigUtil.set("website", "name", params.getOrDefault("name", ""));
        ConfigUtil.set("website", "logo", UrlUtil.toRelativeUrl(params.getOrDefault("logo", "")));
        ConfigUtil.set("website", "favicon", UrlUtil.toRelativeUrl(params.getOrDefault("favicon", "")));
        ConfigUtil.set("website", "backdrop", UrlUtil.toRelativeUrl(params.getOrDefault("backdrop", "")));
    }

    /**
     * 设置版权信息
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void setCopyright(Map<String, String> params) {
        ConfigUtil.set("copyright", "privilege", params.getOrDefault("privilege", ""));
        ConfigUtil.set("copyright", "icpNumber", params.getOrDefault("icpNumber", ""));
        ConfigUtil.set("copyright", "icpLink", params.getOrDefault("icpLink", ""));
        ConfigUtil.set("copyright", "gaNumber", params.getOrDefault("gaNumber", ""));
        ConfigUtil.set("copyright", "gaLink", params.getOrDefault("gaLink", ""));
    }

}
