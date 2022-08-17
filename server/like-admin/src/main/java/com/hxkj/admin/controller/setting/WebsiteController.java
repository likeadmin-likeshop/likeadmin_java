package com.hxkj.admin.controller.setting;

import com.hxkj.admin.service.setting.ISettingWebsiteService;
import com.hxkj.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 网站信息配置管理
 */
@RestController
@RequestMapping("api/setting/website")
public class WebsiteController {

    @Resource
    ISettingWebsiteService iSettingWebsiteService;

    /**
     * 获取网站配置信息
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, String> detail = iSettingWebsiteService.detail();
        return AjaxResult.success(detail);
    }

    /**
     * 保存网站配置信息
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, String> params) {
        iSettingWebsiteService.save(params);
        return AjaxResult.success();
    }

}
