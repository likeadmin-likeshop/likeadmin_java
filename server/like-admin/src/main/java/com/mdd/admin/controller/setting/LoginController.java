package com.mdd.admin.controller.setting;

import com.mdd.admin.service.setting.ISettingLoginService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController("settingLoginController")
@RequestMapping("api/setting/login")
public class LoginController {

    @Resource
    ISettingLoginService iSettingLoginService;

    /**
     * 登录设置详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, Object> map = iSettingLoginService.detail();
        return AjaxResult.success(map);
    }

    /**
     * 登录设置保存
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, String> params) {
        iSettingLoginService.save(params);
        return AjaxResult.success();
    }

}
