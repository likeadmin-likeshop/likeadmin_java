package com.mdd.admin.controller.setting;

import com.mdd.admin.service.ISettingWebsiteService;
import com.mdd.admin.validate.setting.SettingWebsiteValidate;
import com.mdd.admin.vo.setting.SettingWebsiteVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 网站信息配置管理
 */
@RestController
@RequestMapping("api/setting/website")
public class SettingWebsiteController {

    @Resource
    ISettingWebsiteService iSettingWebsiteService;

    /**
     * 获取网站配置信息
     *
     * @author fzr
     * @return AjaxResult<SettingWebsiteVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SettingWebsiteVo> detail() {
        SettingWebsiteVo detail = iSettingWebsiteService.detail();
        return AjaxResult.success(detail);
    }

    /**
     * 保存网站配置信息
     *
     * @author fzr
     * @param websiteValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody SettingWebsiteValidate websiteValidate) {
        iSettingWebsiteService.save(websiteValidate);
        return AjaxResult.success();
    }

}
