package com.mdd.admin.controller.setting;

import com.mdd.admin.config.aop.Log;
import com.mdd.admin.service.setting.ISettingSmsService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 短信设置管理
 */
@RestController
@RequestMapping("api/setting/sms")
public class SmsController {

    @Resource
    ISettingSmsService iSettingSmsService;

    /**
     * 短信引擎列表
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/list")
    public Object list() {
        List<Map<String, Object>> list = iSettingSmsService.list();
        return AjaxResult.success(list);
    }

    /**
     * 短信引擎详情
     *
     * @author fzr
     * @param alias 别名
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(String alias) {
        Map<String, Object> map = iSettingSmsService.detail(alias);
        return AjaxResult.success(map);
    }

    /**
     * 短信引擎保存
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @Log(title = "短信引擎保存")
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, String> params) {
        iSettingSmsService.save(params);
        return AjaxResult.success();
    }

}
