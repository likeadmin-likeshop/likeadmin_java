package com.hxkj.admin.controller.setting;

import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.IBasicsService;
import com.hxkj.common.core.AjaxResult;
import com.hxkj.common.utils.HttpUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 基础设置管理
 */
@RestController
@RequestMapping("/api/setting")
public class BasicsController {

    @Resource
    IBasicsService iBasicsService;

    /**
     * 网站信息
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "网站信息")
    @RequestMapping("/website")
    public Object website(@RequestBody Map<String, String> params) {
        if (HttpUtil.isGet()) {
            Map<String, String> map = iBasicsService.getWebsite();
            return AjaxResult.success(map);
        }

        try {
            iBasicsService.setWebsite(params);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.success(e.getMessage());
        }
    }

    /**
     * 备案信息
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "备案信息")
    @RequestMapping("/copyright")
    public Object copyright(@RequestBody Map<String, String> params) {
        if (HttpUtil.isGet()) {
            Map<String, String> map = iBasicsService.getCopyright();
            return AjaxResult.success(map);
        }

        try {
            iBasicsService.setCopyright(params);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.success(e.getMessage());
        }
    }

}
