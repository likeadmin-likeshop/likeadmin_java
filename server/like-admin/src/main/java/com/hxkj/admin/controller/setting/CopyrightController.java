package com.hxkj.admin.controller.setting;

import com.hxkj.admin.service.setting.ISettingCopyrightService;
import com.hxkj.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 网站版权配置管理
 */
@RestController
@RequestMapping("api/setting/copyright")
public class CopyrightController {

    @Resource
    ISettingCopyrightService iSettingCopyrightService;

    /**
     * 获取网站版权信息
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        List<Map<String, String>> list = iSettingCopyrightService.detail();
        return AjaxResult.success(list);
    }

    /**
     * 保存网站版本信息
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/save")
    public Object save(@RequestBody List<Map<String, String>> params) {
        iSettingCopyrightService.save(params);
        return AjaxResult.success();
    }

}
