package com.hxkj.admin.controller.setting;

import com.hxkj.admin.config.aop.Log;
import com.hxkj.admin.service.IBasicsService;
import com.hxkj.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

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
     * “获取”网站信息
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "网站信息")
    @GetMapping("/getWebsite")
    public Object getWebsite() {
        Map<String, String> map = iBasicsService.getWebsite();
        return AjaxResult.success(map);
    }

    /**
     * “修改”网站信息
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "网站信息")
    @PostMapping("/setWebsite")
    public Object setWebsite(@RequestBody Map<String, String> params) {
        try {
            iBasicsService.setWebsite(params);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.success(e.getMessage());
        }
    }

    /**
     * “获取”备案信息
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "备案信息")
    @GetMapping("/getCopyright")
    public Object getCopyright() {
        Map<String, String> map = iBasicsService.getCopyright();
        return AjaxResult.success(map);
    }

    /**
     * “修改”备案信息
     *
     * @author fzr
     * @return Object
     */
    @Log(title = "备案信息")
    @PostMapping("/setCopyright")
    public Object copyright(@RequestBody Map<String, String> params) {
        try {
            iBasicsService.setCopyright(params);
            return AjaxResult.success();
        } catch (Exception e) {
            return AjaxResult.success(e.getMessage());
        }
    }

}
