package com.mdd.admin.controller.setting;

import com.mdd.admin.service.setting.ISettingUserService;
import com.mdd.common.core.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户设置管理
 */
@RestController("settingUserController")
@RequestMapping("api/setting/user")
public class UserController {

    @Resource
    ISettingUserService iSettingUserService;

    /**
     * 用户设置详情
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail() {
        Map<String, Object> map = iSettingUserService.detail();
        return AjaxResult.success(map);
    }

    /**
     * 用户设置保存
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @PostMapping("/save")
    public Object save(@RequestBody Map<String, String> params) {
        iSettingUserService.save(params);
        return AjaxResult.success();
    }

}
