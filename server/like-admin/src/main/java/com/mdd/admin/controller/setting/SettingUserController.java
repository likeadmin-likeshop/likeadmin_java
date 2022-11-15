package com.mdd.admin.controller.setting;

import com.mdd.admin.service.ISettingUserService;
import com.mdd.admin.validate.setting.SettingUserValidate;
import com.mdd.admin.vo.setting.SettingUserVo;
import com.mdd.common.core.AjaxResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户设置管理
 */
@RestController
@RequestMapping("api/setting/user")
public class SettingUserController {

    @Resource
    ISettingUserService iSettingUserService;

    /**
     * 用户设置详情
     *
     * @author fzr
     * @return AjaxResult<SettingUserVo>
     */
    @GetMapping("/detail")
    public AjaxResult<SettingUserVo> detail() {
        SettingUserVo vo = iSettingUserService.detail();
        return AjaxResult.success(vo);
    }

    /**
     * 用户设置保存
     *
     * @author fzr
     * @param userValidate 参数
     * @return AjaxResult<Object>
     */
    @PostMapping("/save")
    public AjaxResult<Object> save(@Validated @RequestBody SettingUserValidate userValidate) {
        iSettingUserService.save(userValidate);
        return AjaxResult.success();
    }

}
