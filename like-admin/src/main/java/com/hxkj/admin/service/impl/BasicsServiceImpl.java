package com.hxkj.admin.service.impl;

import com.hxkj.admin.service.IBasicsService;
import com.hxkj.common.utils.ConfigUtil;
import com.hxkj.common.utils.UrlUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基础配置服务实现类
 */
@Service
public class BasicsServiceImpl implements IBasicsService {

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
        map.put("icp_number", UrlUtil.toAbsoluteUrl(config.getOrDefault("icp_number", "")));
        map.put("icp_link", UrlUtil.toAbsoluteUrl(config.getOrDefault("icp_link", "")));
        map.put("ga_number", UrlUtil.toAbsoluteUrl(config.getOrDefault("ga_number", "")));
        map.put("ga_link", UrlUtil.toAbsoluteUrl(config.getOrDefault("ga_link", "")));
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
        ConfigUtil.set("copyright", "icp_number", params.getOrDefault("icp_number", ""));
        ConfigUtil.set("copyright", "icp_link", params.getOrDefault("icp_link", ""));
        ConfigUtil.set("copyright", "ga_number", params.getOrDefault("ga_number", ""));
        ConfigUtil.set("copyright", "ga_link", params.getOrDefault("ga_link", ""));
    }

}
