package com.mdd.admin.service.impl;

import com.mdd.admin.service.ISettingWebsiteService;
import com.mdd.admin.validate.setting.SettingWebsiteValidate;
import com.mdd.admin.vo.setting.SettingWebsiteVo;
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
    public SettingWebsiteVo detail() {
        Map<String, String> config = ConfigUtil.get("website");

        SettingWebsiteVo vo = new SettingWebsiteVo();
        vo.setName(config.getOrDefault("name", ""));
        vo.setLogo(UrlUtil.toAbsoluteUrl(config.getOrDefault("logo", "")));
        vo.setFavicon(UrlUtil.toAbsoluteUrl(config.getOrDefault("favicon", "")));
        vo.setBackdrop(UrlUtil.toAbsoluteUrl(config.getOrDefault("backdrop", "")));
        vo.setShopName(config.getOrDefault("shopName", ""));
        vo.setShopLogo(UrlUtil.toAbsoluteUrl(config.getOrDefault("shopLogo", "")));
        return vo;
    }

    /**
     * 保存网站信息
     *
     * @author fzr
     * @param websiteValidate 参数
     */
    @Override
    public void save(SettingWebsiteValidate websiteValidate) {
        ConfigUtil.set("website", "name", websiteValidate.getName());
        ConfigUtil.set("website", "logo", UrlUtil.toRelativeUrl(websiteValidate.getLogo()));
        ConfigUtil.set("website", "favicon", UrlUtil.toRelativeUrl(websiteValidate.getFavicon()));
        ConfigUtil.set("website", "backdrop", UrlUtil.toRelativeUrl(websiteValidate.getBackdrop()));
        ConfigUtil.set("website", "shopName", websiteValidate.getShopName());
        ConfigUtil.set("website", "shopLogo", UrlUtil.toRelativeUrl(websiteValidate.getShopLogo()));
    }

}
