package com.mdd.admin.controller.setting;

import com.mdd.admin.service.ISettingLoginService;
import com.mdd.admin.validate.setting.SettingLoginValidate;
import com.mdd.admin.vo.setting.SettingLoginVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("api/setting/login")
public class SettingLoginController {

    @Resource
    ISettingLoginService iSettingLoginService;

    /**
     * 登录设置详情
     *
     * @author fzr
     * @return AjaxResult<SettingLoginVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SettingLoginVo> detail() {
        SettingLoginVo vo = iSettingLoginService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 登录设置保存
     *
     * @author fzr
     * @param loginValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody SettingLoginValidate loginValidate) {
        iSettingLoginService.save(loginValidate);
        return AjaxResult.success();
    }

}
