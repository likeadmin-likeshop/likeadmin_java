package com.mdd.admin.controller.setting;

import com.mdd.admin.service.setting.ISettingProtocolService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 政策协议配置管理
 */
@RestController
@RequestMapping("api/setting/protocol")
public class ProtocolController {

    @Resource
    ISettingProtocolService iSettingProtocolService;

    /**
     * 获取网站版权信息
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, Map<String, String>> detail = iSettingProtocolService.detail();
        return AjaxResult.success(detail);
    }

    /**
     * 保存网站版本信息
     *
     * @author fzr
     * @return Object
     */
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, Object> params) {
        iSettingProtocolService.save(params);
        return AjaxResult.success();
    }

}
